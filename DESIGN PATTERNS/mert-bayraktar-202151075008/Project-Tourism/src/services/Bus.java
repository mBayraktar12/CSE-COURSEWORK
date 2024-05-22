package services;

public class Bus extends Transportation{
    @Override
    public int getPrice() {
        return 50;
    }

    @Override
    public String toString() {
        return "Private Bus";
    }
}
