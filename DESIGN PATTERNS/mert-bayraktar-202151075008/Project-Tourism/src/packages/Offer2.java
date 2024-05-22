package packages;

import services.*;

public class Offer2 extends PackageBuilder{

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
        p.setA(new Hotel());
    }

    @Override
    public void buildActivities() {
        p.setAct(new SeaCruise());
    }

    // Before builder pattern
    // public Offer2() {
    //     super(new Bus(), new Hotel(), new SeaCruise());
    // }
}

