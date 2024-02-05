import Payments.PaymentStrategy;
import services.Services;

import java.io.IOException;
import java.util.ArrayList;

public class Originator {
    Customer c;
    ArrayList<Services> servicesArrayList;
    PaymentStrategy paymentStrategy;
    CareTaker careTaker;


    public Originator(Customer c, ArrayList<Services> servicesArrayList, CareTaker careTaker,PaymentStrategy paymentStrategy) {
        this.c = c;
        this.servicesArrayList = servicesArrayList;
        this.careTaker = careTaker;
        this.paymentStrategy = paymentStrategy;
    }
    // Method to create and save a reservation using the Memento pattern.

    public void createReservation(String phoneNumber) throws IOException {
                // Save a Memento (snapshot) of the reservation state.
        // This allows us to restore the state later if needed
        careTaker.saveMemento(
                phoneNumber,
                new Reservation(c, servicesArrayList,paymentStrategy)
        );
    }
}
