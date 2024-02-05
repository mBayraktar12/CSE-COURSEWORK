
import torch

class MLP:
    def __init__(
        self,
        linear_1_in_features,
        linear_1_out_features,
        f_function,
        linear_2_in_features,
        linear_2_out_features,
        g_function
    ):
        """
        Args:
            linear_1_in_features: the in features of first linear layer
            linear_1_out_features: the out features of first linear layer
            linear_2_in_features: the in features of second linear layer
            linear_2_out_features: the out features of second linear layer
            f_function: string for the f function: relu | sigmoid | identity
            g_function: string for the g function: relu | sigmoid | identity
        """
        self.f_function = f_function
        self.g_function = g_function

        self.funcs = {"relu": relu, "sigmoid": sigmoid, "identity": identity}

        self.parameters = dict(
            W1 = torch.randn(linear_1_out_features, linear_1_in_features),
            b1 = torch.randn(linear_1_out_features),
            W2 = torch.randn(linear_2_out_features, linear_2_in_features),
            b2 = torch.randn(linear_2_out_features),
        )
        self.grads = dict(
            dJdW1 = torch.zeros(linear_1_out_features, linear_1_in_features),
            dJdb1 = torch.zeros(linear_1_out_features),
            dJdW2 = torch.zeros(linear_2_out_features, linear_2_in_features),
            dJdb2 = torch.zeros(linear_2_out_features),
        )

        # put all the cache value you need in self.cache
        self.cache = dict()

    def forward(self, x):
        """
        Args:
            x: tensor shape (batch_size, linear_1_in_features)
        """
        self.cache['x'] = x

        self.cache["z1"] = torch.matmul(self.cache["x"], self.parameters["W1"].T) + self.parameters["b1"]
        self.cache["z2"] = self.funcs[self.f_function](self.cache["z1"])

        self.cache["z3"] = torch.matmul(self.cache["z2"], self.parameters["W2"].T) + self.parameters["b2"]

        self.cache["y_hat"] = self.funcs[self.g_function](self.cache["z3"])

        return self.cache["y_hat"]

    def backward(self, dJdy_hat):
        """
        Args:
            dJdy_hat: The gradient tensor of shape (batch_size, linear_2_out_features)
        """
        # TODO: Implement the backward function
        if self.g_function == 'relu':
            dJdz3 = dJdy_hat.clone() * (self.cache["z3"]>0)

        elif self.g_function == 'sigmoid':
            dJdz3 = dJdy_hat * self.cache["y_hat"] * (1 - self.cache["y_hat"])

        elif self.g_function == 'identity':
            dJdz3 = dJdy_hat.clone()
        else:
            raise Exception('g_function {} is not allowed.'.format(self.f_function))
        
        self.grads["dJdW2"] = torch.matmul(dJdz3.T, self.cache["z2"])
        self.grads["dJdb2"] = torch.matmul(dJdz3.T, torch.ones(dJdz3.shape[0]))

        dJdz2 = torch.matmul(dJdz3, self.parameters["W2"])

        if self.f_function == 'relu':
            dJdz1 = dJdz2 * (self.cache["z1"] > 0)
        elif self.f_function == 'sigmoid':
            dJdz1 = dJdz2 * self.cache["z2"] * (1 - self.cache["z2"])
        elif self.f_function == 'identity':
            dJdz1 = dJdz2.clone()
        else:
            raise Exception('f_function {} is not allowed.'.format(self.f_function))
        
        self.grads["dJdW1"] = torch.matmul(dJdz1.T, self.cache["x"])
        self.grads["dJdb1"] = torch.matmul(dJdz1.T, torch.ones(dJdz1.shape[0]))

        return dJdy_hat

    def clear_grad_and_cache(self):
        for grad in self.grads:
            self.grads[grad].zero_()
        self.cache = dict()

def relu(z):
  return torch.where(z < 0, 0, z)

def identity(z):
  return z

def sigmoid(z):
  return 1 / (1 + torch.exp(-z))


def mse_loss(y, y_hat):
    """
    Args:
        y: the label tensor (batch_size, linear_2_out_features)
        y_hat: the prediction tensor (batch_size, linear_2_out_features)

    Return:
        J: scalar of loss
        dJdy_hat: The gradient tensor of shape (batch_size, linear_2_out_features)
    """
    diff = y_hat - y
    loss = (diff @ diff.T).norm()
    dJdy_hat = (2 * diff) / (y.size()[0] * y.size()[1])

    return loss, dJdy_hat

def bce_loss(y, y_hat):
    """
    Args:
        y_hat: the prediction tensor
        y: the label tensor

    Return:
        loss: scalar of loss
        dJdy_hat: The gradient tensor of shape (batch_size, linear_2_out_features)
    """
    loss = - (torch.mul(y, torch.log(y_hat)) + torch.mul(1 - y, torch.log(1-y_hat)))
    dJdy_hat = (- y / y_hat + (1-y) / (1-y_hat)) / (y.size()[0] * y.size()[1])  

    return loss, dJdy_hat