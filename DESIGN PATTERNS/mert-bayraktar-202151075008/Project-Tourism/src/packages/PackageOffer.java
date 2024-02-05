// package packages;

// import services.Accommodations;
// import services.Activities;
// import services.Transportation;

// import java.io.Serializable;

// public abstract class PackageOffer implements Serializable {

//     public Transportation t;
//     public Accommodations a;
//     public Activities act;

//     public PackageOffer(Transportation t, Accommodations a, Activities act) {
//         this.t = t;
//         this.a = a;
//         this.act = act;
//     }

//     public int getTotalPricee(){
//         int totalPrice = t.getPrice() + a.getPrice() + act.getPrice();
//         return totalPrice;
//     }

//     @Override
//     public String toString() {
//         return
//                 "Transportation: " + t +
//                 "Accommodation: " + a +
//                 "Activities:" + act
//                 ;
//     }
// }


//NO NEED TO USE THIS