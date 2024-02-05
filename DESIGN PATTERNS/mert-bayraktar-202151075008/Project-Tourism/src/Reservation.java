import Payments.PaymentStrategy;
import services.Services;

import java.io.Serializable;
import java.util.ArrayList;

//MEMENTO CLASS

public class Reservation implements Serializable {
    Customer c;
    ArrayList<Services> servicesArrayList;

    // Strategy Design Pattern
    PaymentStrategy paymentStrategy;

    public Reservation(Customer c, ArrayList<Services> servicesArrayList, PaymentStrategy strategy) {
        this.c = c;
        this.servicesArrayList = servicesArrayList;
        this.paymentStrategy = strategy;
    }

    public Customer getC() {
        return c;
    }

    public void setC(Customer c) {
        this.c = c;
    }

    public ArrayList<Services> getServicesArrayList() {
        return servicesArrayList;
    }

    public void setServicesArrayList(ArrayList<Services> servicesArrayList) {
        this.servicesArrayList = servicesArrayList;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }


    public void pay(int totalAmount){
        paymentStrategy.pay(totalAmount);
    }

    @Override
    public String toString() {
        return   c.toString() + "\n"+
                 servicesArrayList.toString()+ "\n"+
                 paymentStrategy.toString();


    }
}
