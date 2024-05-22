package Payments;

import java.io.Serializable;

public interface PaymentStrategy extends Serializable {
    public void pay(int amount);
}
