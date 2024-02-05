package services;

public class AdventureSeaCruise extends ServiceDecorator {
    public AdventureSeaCruise(Services decoratedService) {
        super(decoratedService);
    }

    @Override
    public int getPrice() {
        // Add extra cost for adventure features
        return 500;
    }

    @Override
    public String toString() {
        return "Adventure Sea Cruise";
    }
}
