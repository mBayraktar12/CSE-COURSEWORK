package packages;

import services.*;

public class Offer3 extends PackageBuilder{

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void buildTransportation() {
        p.setT(new Bus());
    }

    @Override
    public void buildAccommodation() {
        p.setA(new Motel());
    }

    @Override
    public void buildActivities() {
        p.setAct(new SkyDiving());
    }
}