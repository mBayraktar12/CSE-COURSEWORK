package packages;

public abstract class PackageBuilder {
    
    Package p;
// Method to create a new Package and build its components.
    public void createPackage(){
        p = new Package();
        buildTransportation();
        buildAccommodation();
        buildActivities();
    }
 // Abstract methods to be implemented by concrete package builders.
    public abstract void buildTransportation();
    public abstract void buildAccommodation();
    public abstract void buildActivities();

    public Package getP() {
        return p;
    }

    public void setP(Package p) {
        this.p = p;
    }
}
