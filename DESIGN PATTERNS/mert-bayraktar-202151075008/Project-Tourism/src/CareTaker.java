import java.io.*;
import java.util.TreeMap;

public class CareTaker {

    TreeMap<String, Reservation> newMaptoSave = new TreeMap<>();
    File file = new File("C:/Users/admin/OneDrive/Desktop/Deneme Proje/myfile.dat");


    public void saveMemento(String phoneNumber,Reservation reservation) throws IOException {
        newMaptoSave.put(phoneNumber, reservation);

        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(newMaptoSave);
        oos.flush();
        oos.close();
    }
}
