import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = 4355656705997622378L;
    String name;
    String phone;
    String age;
    String job;

    boolean isIndividual;
    boolean userWantsLuxuryHotelOrNot;
    boolean userWantsLuxuryTaxiOrNot;
    boolean userWantsAdventureSeaCruiseOrNot;

    public Customer(String name, String phone, String age, String job, boolean isIndividual, boolean userWantsLuxuryHotelOrNot, boolean userWantsLuxuryTaxiOrNot, boolean userWantsAdventureSeaCruiseOrNot) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.job = job;
        this.isIndividual = isIndividual;
        this.userWantsLuxuryHotelOrNot = userWantsLuxuryHotelOrNot;
        this.userWantsLuxuryTaxiOrNot = userWantsLuxuryTaxiOrNot;
        this.userWantsAdventureSeaCruiseOrNot = userWantsAdventureSeaCruiseOrNot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isIndividual() {
        return isIndividual;
    }

    public void setIndividual(boolean individual) {
        isIndividual = individual;
    }

    public boolean userWantsLuxuryHotelOrNot() {
        return userWantsLuxuryHotelOrNot;
    }
    
    public void setUserWantLuxuryHotelOrNot(boolean luxuryHotel) {
        userWantsLuxuryHotelOrNot = luxuryHotel;
    }

    public boolean userWantsLuxuryTaxiOrNot() {
        return userWantsLuxuryTaxiOrNot;
    }
    
    public void setUserWantLuxuryTaxiOrNot(boolean luxuryTaxi) {
        userWantsLuxuryTaxiOrNot = luxuryTaxi;
    }

    public boolean userWantsAdventureSeaCruiseOrNot() {
        return userWantsAdventureSeaCruiseOrNot;
    }
    
    public void setUserWantsAdventureSeaCruiseOrNot(boolean adventureSeaCruise) {
        userWantsLuxuryHotelOrNot = adventureSeaCruise;
    }


    @Override
    public String toString() {
        return  "  name= " + name + "\n" +
                "  phone= " + phone + "\n" +
                "  age= " + age + "\n" +
                "  job=" + job + "\n" +
                "  isIndividual=" + isIndividual + "\n" +
                "  userWantsLuxuryHotelOrNot=" + userWantsLuxuryHotelOrNot + "\n" +
                "  userWantsLuxuryTaxiOrNot=" + userWantsLuxuryTaxiOrNot + "\n" +
                "  userWantsAdventureSeaCruiseOrNot=" + userWantsAdventureSeaCruiseOrNot + "\n";

    }
}
