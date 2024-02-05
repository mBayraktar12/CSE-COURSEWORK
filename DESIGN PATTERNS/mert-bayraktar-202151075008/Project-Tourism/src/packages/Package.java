package packages;

import services.Accommodations;
import services.Activities;
import services.Transportation;

public  class Package {

    Transportation t;
    Accommodations a;
    Activities act;

    public void setA(Accommodations a) {
        this.a = a;
    }

    public void setT(Transportation t) {
        this.t = t;
    }

    public void setAct(Activities act) {
        this.act = act;
    }

    public Transportation getT() {
        return t;
    }

    public Accommodations getA() {
        return a;
    }

    public Activities getAct() {
        return act;
    }

    public int getTotalPricee(){
        int totalPrice = t.getPrice() + a.getPrice() +act.getPrice();
        return totalPrice;
    }

}
