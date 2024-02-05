package services;


public class LuxuryTaxi extends ServiceDecorator{
    public LuxuryTaxi(Services decoratedService) {
        super(decoratedService);
    }

    @Override
    public int getPrice() {
        // Add extra cost for luxury features
        return 300;
    }
    @Override
    public String toString() {
        return "Luxury Taxi";
    }
}