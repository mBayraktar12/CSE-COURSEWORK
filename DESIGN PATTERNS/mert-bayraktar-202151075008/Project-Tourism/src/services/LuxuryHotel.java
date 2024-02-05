package services;

public class LuxuryHotel extends ServiceDecorator{ 

    public LuxuryHotel(Services decoratedService) {
        super(decoratedService);
    }

    @Override
    public int getPrice() {
        return 300;
    }

    @Override
    public String toString() {
        return "Luxury Hotel";
    }
    
}
