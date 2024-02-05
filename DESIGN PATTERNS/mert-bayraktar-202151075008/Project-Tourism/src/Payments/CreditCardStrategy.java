package Payments;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class CreditCardStrategy implements PaymentStrategy, Serializable {

    String name;
    String cardNumber;
    String cvv;
    String dateOfExpiry;

    public CreditCardStrategy(String name, String cardNumber, String cvv, String dateOfExpiry) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;
    }

    @Override
    public void pay(int amount) {

        System.out.println("Payment done successfully with Credit Card");
        JOptionPane.showMessageDialog(null, "Payment done successfull with CreditCard");

    }

    @Override
    public String toString() {
        return "Credit Card Details: \n" +
                " name=" + name + "\n" +
                " cardNumber= " + cardNumber + "\n" +
                " cvv=" + cvv + "\n" +
                " dateOfExpiry= " + dateOfExpiry + "\n"
                ;
    }
}
