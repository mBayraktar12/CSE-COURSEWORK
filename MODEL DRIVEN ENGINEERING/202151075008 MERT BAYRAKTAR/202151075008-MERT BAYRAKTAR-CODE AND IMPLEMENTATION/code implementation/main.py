from mesa import Agent, Model
from mesa.space import SingleGrid
from mesa.time import RandomActivation
from mesa.visualization.modules import CanvasGrid
from mesa.visualization.ModularVisualization import ModularServer
import random
import math

# Define the IndoorAgent representing the indoor environment
class IndoorAgent(Agent):
    def __init__(self, unique_id, model):
        super().__init__(unique_id, model)
        self.rssi_values = {}  # Dictionary to store RSSI values from mobile agents
    
    def step(self):
        # Add logic for indoor environment behavior
        print("Indoor agent", self.unique_id, "is stepping")
        mobile_agents = [
            agent for agent in self.model.schedule.agents if isinstance(agent, MobileAgent)
        ]
        for mobile_agent in mobile_agents:
            distance = self.calculate_distance(mobile_agent.pos)
            rssi_value = self.calculate_rssi(distance)
            self.rssi_values[mobile_agent.unique_id] = rssi_value
    
    def calculate_distance(self, mobile_agent_pos):
        # Calculate distance between indoor agent and mobile agent
        x1, y1 = self.pos
        x2, y2 = mobile_agent_pos
        distance = math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)
        return distance
    
    def calculate_rssi(self, distance):
        # Calculate RSSI value based on distance and signal propagation model
        tx_power = -10  # Reference transmission power in dBm
        n = 2.0  # Path loss exponent
        rssi_value = tx_power - (10 * n * math.log10(distance))
        return rssi_value

# Define the MobileAgent representing the mobile device
class MobileAgent(Agent):
    def __init__(self, unique_id, model):
        super().__init__(unique_id, model)
        
    def step(self):
        # Add logic for mobile device behavior
        x, y = self.random_move()
        print("Mobile agent", self.unique_id, "is moving to", x, y)
        self.model.grid.move_agent(self, (x, y))
        self.predict_location()

    def random_move(self):
        possible_moves = self.model.grid.get_neighborhood(
            self.pos,
            moore=True,
            include_center=False
        )
        return random.choice(possible_moves)
    
    def predict_location(self):
        # Predict the mobile agent's location based on RSSI values
        indoor_agents = [
            agent for agent in self.model.schedule.agents if isinstance(agent, IndoorAgent)
        ]
        closest_agent = None
        closest_distance = float('inf')
        for agent in indoor_agents:
            rssi_value = agent.rssi_values.get(self.unique_id, None)
            if rssi_value is not None:
                distance = self.calculate_distance(rssi_value)
                if distance < closest_distance:
                    closest_agent = agent
                    closest_distance = distance
        if closest_agent is not None:
            predicted_location = (closest_agent.pos[0], closest_agent.pos[1], closest_distance)
            print("Mobile agent", self.unique_id, "predicted location:", predicted_location)
        else:
            print("Mobile agent", self.unique_id, "could not predict the location.")
    
    def calculate_distance(self, rssi_value):
        # Calculate distance based on RSSI value and signal propagation model
        tx_power = -10  # Reference transmission power in dBm
        n = 2.0  # Path loss exponent
        distance = 10 ** ((tx_power - rssi_value) / (10 * n))
        return distance

# Define the IndoorLocalizationModel representing the simulation model
class IndoorLocalizationModel(Model):
    def __init__(self, n_indoor_agents, n_mobile_agents, width, height):
        self.num_indoor_agents = n_indoor_agents
        self.num_mobile_agents = n_mobile_agents
        self.grid = SingleGrid(width, height, torus=False)
        self.schedule = RandomActivation(self)
        
        # Create indoor agents
        empty_cells = list(self.grid.empties)
        for i in range(self.num_indoor_agents):
            cell = random.choice(empty_cells)
            empty_cells.remove(cell)
            indoor_agent = IndoorAgent(i, self)
            self.schedule.add(indoor_agent)
            self.grid.place_agent(indoor_agent, cell)
        
        # Create mobile agents
        for i in range(self.num_mobile_agents):
            x = random.randrange(self.grid.width)
            y = random.randrange(self.grid.height)
            mobile_agent = MobileAgent(self.num_indoor_agents + i, self)
            self.schedule.add(mobile_agent)
            self.grid.place_agent(mobile_agent, (x, y))
    
    def step(self):
        self.schedule.step()

# Define the portrayal function for visualization
def agent_portrayal(agent):
    if isinstance(agent, IndoorAgent):
        return {
            "Shape": "rect",
            "Color": "red",
            "Filled": "true",
            "Layer": 0,
            "w": 1,
            "h": 1
        }
    elif isinstance(agent, MobileAgent):
        return {
            "Shape": "circle",
            "Color": "blue",
            "Filled": "true",
            "Layer": 1,
            "r": 0.5,
            "text": f"{agent.pos[0]},{agent.pos[1]}"
        }

# Create an instance of the IndoorLocalizationModel
n_indoor_agents = 10
n_mobile_agents = 1
width = 20
height = 20
model = IndoorLocalizationModel(n_indoor_agents, n_mobile_agents, width, height)

# Create the grid visualization
canvas_element = CanvasGrid(agent_portrayal, width, height, 500, 500)

# Create the server for visualization
server = ModularServer(IndoorLocalizationModel, [canvas_element], "Indoor Localization Simulation",
                       {"n_indoor_agents": n_indoor_agents, "n_mobile_agents": n_mobile_agents, "width": width, "height": height})

# Run the simulation
server.launch()


# The three values in the predicted location (4, 11, 3.1622776601683795) represent the following:

# The first value 4 represents the x-coordinate of the predicted location.
# The second value 11 represents the y-coordinate of the predicted location.
# The third value 3.1622776601683795 represents the distance between the mobile agent and the closest indoor agent. 
# This distance is calculated based on the RSSI value received from the indoor agent using the signal propagation model.
# So, in summary, the predicted location (4, 11) suggests that the mobile agent predicts its location to be at coordinates 
# (4, 11) on the grid, and the distance between the mobile agent and the closest indoor agent is approximately 3.16 units.