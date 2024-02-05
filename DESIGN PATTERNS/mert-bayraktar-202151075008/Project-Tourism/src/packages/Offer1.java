package packages;

import services.*;

public class Offer1 extends PackageBuilder{

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void buildTransportation() {
        p.setT(new Taxi());
    }

    @Override
    public void buildAccommodation() {
        p.setA(new Hotel());
    }

    @Override
    public void buildActivities() {
        p.setAct(new SkyDiving());
    }

}
