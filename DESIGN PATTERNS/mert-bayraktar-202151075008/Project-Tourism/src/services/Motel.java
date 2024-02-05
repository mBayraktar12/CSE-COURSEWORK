package services;

public class Motel extends Accommodations{
    @Override
    public int getPrice() {
        return 80;
    }

    @Override
    public String toString() {
        return "4 stars Motel";
    }
}
