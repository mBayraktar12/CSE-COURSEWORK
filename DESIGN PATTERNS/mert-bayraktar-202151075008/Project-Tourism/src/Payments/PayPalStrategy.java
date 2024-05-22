package Payments;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class PayPalStrategy implements PaymentStrategy, Serializable {

    String email;
    String pass;

    public PayPalStrategy(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Payment done successfully with PayPal");
        JOptionPane.showMessageDialog(null, "Payment done successfully with Paypal");
    }

    @Override
    public String toString() {
        return "PayPal Details:" +"\n" +
                "email= " + email + "\n" +
                "pass= " + pass + "\n"
                ;
    }
}