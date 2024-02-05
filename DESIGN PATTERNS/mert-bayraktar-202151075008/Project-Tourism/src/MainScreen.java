import Payments.CreditCardStrategy;
import Payments.PayPalStrategy;
import Payments.PaymentStrategy;
import packages.*;
import services.*;

import javax.swing.*;
import java.text.ParseException;

import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class MainScreen extends JFrame implements ChatObserver{
    // Global Variables
    public int packagePrice = 0;
    public boolean isIndividualOrNot = false;

    public boolean userWantsLuxuryTaxiOrNot = false;
    public boolean userWantsAdventureSeaCruiseOrNot = false;
    public boolean userWantsLuxuryHotelOrNot = false;

    // Customization
    Font myFont = new Font("Arial", Font.BOLD, 20);
    Font myFont2 = new Font("Arial", Font.BOLD, 16);
    Color myColor = new Color(0, 102, 204);


    // Panel 1
    JTextField cusName;
    JTextField cusPhone;
    JTextField cusAge;
    JTextField cusJob;
    JRadioButton isIndividual;
    JRadioButton isGroup;
    ButtonGroup G0;

    JComboBox<String> comboBox;

    // Panel 2
    JRadioButton package1RadioBTN;
    JRadioButton package2RadioBTN;
    JRadioButton package3RadioBTN;
    ButtonGroup G1;

    // Panel 3.
    JLabel transLBL;
    JLabel activityLBL;
    JLabel AccomLBL;
    JComboBox<String> comboBox1;
    JComboBox<String> comboBox2;
    JComboBox<String> comboBox3;
    JRadioButton luxuryTaxiButton;
    JRadioButton luxuryHotelButton;
    JRadioButton adventureCruiseButton;


    // Panel 4
    PaymentStrategy paymentStrategy;
    JRadioButton payment1RadioBTN;
    JRadioButton payment2RadioBTN;
    JTextArea totalPriceLBL;
    ButtonGroup g2;

    // Panel 5
    JTextArea detailTransArea;
    JTextArea detailActivityArea;
    JTextArea detailAccomArea;


    // Panel 6
    JButton chatBtn;
    JButton makeReservationBTN;
    JLabel searchLBL;
    JTextField searchField;
    JButton makeSearch;
    JTextArea reservationDetailsArea;

    //Panel 8
    JTextArea userChatTextArea;
    JTextArea supportChatTextArea;
    JTextField chatInputField;
    JButton userSendChatButton;
    JButton supportSendChatButton;

    ChatSubject userChatSubject;
    ChatSubject supportChatSubject;


    public MainScreen() {

        CustomizePanel1();
        CustomizePanel2();
        CustomizePanel3();

        CustomizePanel4();
        CustomizePanel5();
        CustomizePanel6();

        CustomizePanel7();
        CustomizePanel8();
        userChatSubject = new ChatSubject();
        supportChatSubject = new ChatSubject();

        userChatSubject.addObserver(this, "User");
        supportChatSubject.addObserver(this, "Support");

    }

    private void CustomizePanel1() {
        /************************ Customer Panel ******************************/

        JPanel p1 = new JPanel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "  Customer  ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, myFont, myColor);
        p1.setBorder(titledBorder);


        JLabel fNameLBL = new JLabel(" Name");
        JLabel LNameLBL = new JLabel(" Phone");
        JLabel CityLBL = new JLabel(" Age");
        JLabel phoneLBL = new JLabel(" Job");
        JLabel isIndividualLBL = new JLabel(" Individual Trip?");

        cusName = new JTextField();
        cusName.setOpaque(false);
        cusPhone = new JTextField();
        cusPhone.setOpaque(false);
        cusAge = new JTextField();
        cusAge.setOpaque(false);
        cusJob = new JTextField();
        cusJob.setOpaque(false);

        isIndividual = new JRadioButton("Individual");
        isGroup = new JRadioButton("Group");


        p1.add(fNameLBL);
        p1.add(cusName);
        p1.add(LNameLBL);
        p1.add(cusPhone);
        p1.add(CityLBL);
        p1.add(cusAge);
        p1.add(phoneLBL);


        // Drop down menu
        String[] jobs = {"Student", "Business", "Retired","Employee"};
        comboBox = new JComboBox<>(jobs);
        comboBox.setBounds(120,10,80,20);
        p1.add(comboBox);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBox.getSelectedItem();
                System.out.println("Selected "+selected);
            }
        });


        p1.add(isIndividual);
        p1.add(isGroup);
        G0 = new ButtonGroup();
        G0.add(isGroup);
        G0.add(isIndividual);

        isIndividual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                isIndividualOrNot = true;

                // Disable the offers
                package1RadioBTN.setEnabled(false);
                package2RadioBTN.setEnabled(false);
                package3RadioBTN.setEnabled(false);
                comboBox1.setEnabled(true);
                comboBox2.setEnabled(true);
                comboBox3.setEnabled(true);
                luxuryTaxiButton.setEnabled(true);
                luxuryHotelButton.setEnabled(true);
                adventureCruiseButton.setEnabled(true);
            }
        });
        isGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isIndividualOrNot = false;
                package1RadioBTN.setEnabled(true);
                package2RadioBTN.setEnabled(true);
                package3RadioBTN.setEnabled(true);

                comboBox1.setEnabled(false);
                comboBox2.setEnabled(false);
                comboBox3.setEnabled(false);
                luxuryTaxiButton.setEnabled(false);
                luxuryHotelButton.setEnabled(false);
                adventureCruiseButton.setEnabled(false);
            }
        });

        p1.setBounds(15, 15, 300, 200);
        p1.setLayout(new GridLayout(5, 2));

        /************************ Adding panels to frame ******************************/
        setLayout(null);
        add(p1);

    }

    private void CustomizePanel2()  {
        /************************ Vehicle Panel ******************************/
        JPanel p2 = new JPanel();
        TitledBorder titledBorder2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "  Special Packages  ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, myFont, myColor);
        p2.setBorder(titledBorder2);


        package1RadioBTN = new JRadioButton("Package 1");
        package2RadioBTN = new JRadioButton("Package 2");
        package3RadioBTN = new JRadioButton("Package 3");

        G1 = new ButtonGroup();
        G1.add(package1RadioBTN);
        G1.add(package2RadioBTN);
        G1.add(package3RadioBTN);

        package1RadioBTN.setActionCommand("pack1");
        package2RadioBTN.setActionCommand("pack2");
        package3RadioBTN.setActionCommand("pack3");


        package1RadioBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PackageBuilder offer1 = new Offer1();
                offer1.createPackage();
                System.out.println("O1: "+offer1.getP().getTotalPricee());

                detailAccomArea.setText("Accommodation: 5 Stars Hotel \nAll Inclusive");
                detailActivityArea.setText("Activities: Gorgeous \nSky Diving \nExperience");
                detailTransArea.setText("Transportation: Uber Taxi");

                totalPriceLBL.setText("Total Price: " +offer1.getP().getTotalPricee() + " $");
            }
        });

        package2RadioBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PackageBuilder offer2 = new Offer2();
                offer2.createPackage();
                System.out.println("O2: "+offer2.getP().getTotalPricee());

                detailAccomArea.setText("Accommodation: 4 Stars Hotel \nAll Inclusive");
                detailActivityArea.setText("Activities: Unforgettable \nSea Cruise");
                detailTransArea.setText("Transportation: Yandex Bus");

                totalPriceLBL.setText("Total Price: " +offer2.getP().getTotalPricee() + " $");
            }
        });
        package3RadioBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PackageBuilder offer3 = new Offer3();
                offer3.createPackage();
                System.out.println("O3: "+offer3.getP().getTotalPricee());

                detailAccomArea.setText("Accommodation: 4 Stars Motel \nAll Inclusive");
                detailActivityArea.setText("Activities: Unforgettable \nSky Diving");
                detailTransArea.setText("Transportation: Yandex Bus");

                totalPriceLBL.setText("Total Price: " +offer3.getP().getTotalPricee()
                        + " $");
            }
        });




        p2.add(package1RadioBTN);
        p2.add(package2RadioBTN);
        p2.add(package3RadioBTN);

        p2.setBounds(15, 250, 300, 200);
        p2.setLayout(new GridLayout(3, 1));

        setLayout(null);
        add(p2);

    }

    private void CustomizePanel3(){

        JPanel p3 = new JPanel();
        TitledBorder titledBorder3 = BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.GRAY, 1),
                        "  Individual Trips  ", TitledBorder.CENTER,
                        TitledBorder.DEFAULT_POSITION, myFont, myColor);
        
        p3.setBorder(titledBorder3);

        luxuryTaxiButton = new JRadioButton("Luxury Taxi");
        adventureCruiseButton = new JRadioButton("Adventure Sea Cruise");
        luxuryHotelButton = new JRadioButton("Luxury Hotel");

        p3.add(luxuryHotelButton);
        p3.add(luxuryTaxiButton);
        p3.add(adventureCruiseButton);

        transLBL = new JLabel("Transportation");
        AccomLBL = new JLabel("Accommodation");
        activityLBL = new JLabel("Activity");


        p3.add(transLBL);

        luxuryTaxiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userWantsLuxuryTaxiOrNot = true;
            }
        });

        adventureCruiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userWantsAdventureSeaCruiseOrNot = true;
            }
        });

        luxuryHotelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userWantsLuxuryHotelOrNot = true;
            }
        });


        // Transportation
        String[] transportations = {"Taxi", "Bus"};
        comboBox1 = new JComboBox<>(transportations);
        comboBox1.setBounds(120,10,80,20);
        p3.add(comboBox1);

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String selected = (String) comboBox1.getSelectedItem();
//                System.out.println("Sleected "+selected);
                if (selected.equals("Taxi")){
                    Taxi t1 =new Taxi();
                }
            }
        });

        p3.add(activityLBL);

        // Activities
        String[] activities = {"Sea Cruise", "Sky Diving"};
        comboBox2 = new JComboBox<>(activities);
        comboBox2.setBounds(120,10,80,20);
        p3.add(comboBox2);

        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });


        p3.add(AccomLBL);
        // Accommodations
        String[] accommodations = {"Hotel", "Motel"};
        comboBox3 = new JComboBox<>(accommodations);
        comboBox3.setBounds(120,10,80,20);
        p3.add(comboBox3);

        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        p3.setBounds(15, 500, 300, 150);
        p3.setLayout(new GridLayout(3, 2));

        setLayout(null);
        add(p3);

    }
    private void CustomizePanel4(){

        JPanel p4 = new JPanel();
        TitledBorder titledBorder4 = BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.GRAY, 1),
                        "  Payment Methods  ",
                        TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, myFont,
                        myColor);

        p4.setBorder(titledBorder4);

        totalPriceLBL = new JTextArea("Total Price: ______  $");
        totalPriceLBL.setOpaque(false);
        totalPriceLBL.setFont(myFont2);
        payment1RadioBTN = new JRadioButton("PayPal");
        payment2RadioBTN = new JRadioButton("Credit Card");
        g2 = new ButtonGroup();
        g2.add(payment1RadioBTN);
        g2.add(payment2RadioBTN);


        payment1RadioBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentStrategy = new PayPalStrategy(cusName.getText()+"@gmail.com","12345");
            }
        });
        payment2RadioBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentStrategy = new CreditCardStrategy(
                        cusName.getText(),
                        "1234 5678 9123 4561",
                        "999",
                        "12/24"
                );
            }
        });


        p4.add(payment1RadioBTN);
        p4.add(payment2RadioBTN);
        p4.add(totalPriceLBL);
        p4.setBounds(330, 15, 300, 400);
        p4.setLayout(new GridLayout(3, 1));

        /************************ Adding panels to frame ******************************/
        setLayout(null);
        add(p4);

    }

    private void CustomizePanel5(){
        JPanel p5 = new JPanel();
        TitledBorder titledBorder5 = BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.GRAY, 1),
                        "  Service Details  ",
                        TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, myFont,
                        myColor);

        p5.setBorder(titledBorder5);


        detailTransArea = new JTextArea();
        detailActivityArea = new JTextArea();
        detailAccomArea = new JTextArea();

        detailAccomArea.setOpaque(false);
        detailActivityArea.setOpaque(false);
        detailTransArea.setOpaque(false);

        detailAccomArea.setEnabled(false);
        detailTransArea.setEnabled(false);
        detailActivityArea.setEnabled(false);

        detailAccomArea.setFont(myFont);
        detailTransArea.setFont(myFont);
        detailActivityArea.setFont(myFont);


        p5.add(detailTransArea);
        p5.add(detailActivityArea);
        p5.add(detailAccomArea);

        p5.setBounds(330, 450, 300, 300);
        p5.setLayout(new GridLayout(3,1));

        /************************ Adding panels to frame ******************************/
        setLayout(null);
        add(p5);
    }

    private void CustomizePanel6() {
        JPanel p6 = new JPanel();
        TitledBorder titledBorder6 = BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.GRAY, 1),
                        "  Agency Control Panel  ",
                        TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, myFont,
                        myColor);

        p6.setBorder(titledBorder6);


        chatBtn = new JButton("Start Live Chat");
        makeReservationBTN = new JButton("Make Reservation");
        searchLBL = new JLabel("Search Customer by Phone");
        searchField = new JTextField();
        makeSearch = new JButton("Search Customer");

        p6.add(chatBtn);
        p6.add(makeReservationBTN);
        p6.add(searchLBL);
        p6.add(searchField);
        p6.add(makeSearch);


        makeReservationBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MakeReservation();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        makeSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchCustomerbyMobileNb();
            }
        });


        p6.setBounds(660, 15, 300, 300);
        p6.setLayout(new GridLayout(10, 1));

        setLayout(null);
        add(p6);
    }

    public void CustomizePanel7(){
        JPanel p7 = new JPanel();
        TitledBorder titledBorder7 = BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.GRAY, 1),
                        "  Reservations Details  ",
                        TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, myFont,
                        myColor);

        p7.setBorder(titledBorder7);

        reservationDetailsArea = new JTextArea();
        reservationDetailsArea.setOpaque(false);
        reservationDetailsArea.setEnabled(false);
        reservationDetailsArea.setFont(myFont2);

        JScrollPane scrollableTextArea = new JScrollPane(reservationDetailsArea);
        p7.add(scrollableTextArea);


        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        p7.setBounds(660, 330, 300, 300);
        p7.setLayout(new GridLayout(1,1));

        setLayout(null);
        add(p7);
    }

    private void MakeReservation() throws ParseException, IOException {
        // Template Method Design Pattern
        Customer c = CreateCustomer();
        ArrayList<Services> servicesArrayList = GetChoosenServices();

        SaveReservationToDisk();

    }


    private Customer CreateCustomer() {
        Customer c = new Customer(
                cusName.getText(),
                cusPhone.getText(),
                cusAge.getText(),
                comboBox.getSelectedItem().toString(),
                isIndividualOrNot,
                userWantsLuxuryHotelOrNot,
                userWantsLuxuryTaxiOrNot,
                userWantsAdventureSeaCruiseOrNot
        );
        System.out.println(""+c.toString());
        return c;
    }

    private ArrayList<Services> GetChoosenServices() {

        ArrayList<Services> servicesArrayList = new ArrayList<>();

        if (isIndividualOrNot){
            Transportation t;
            Activities act;
            Accommodations a;

            Services baseService1 = null;
            Services baseService2 = null;
            Services baseService3 = null;

            String selected1 = (String) comboBox1.getSelectedItem();
            String selected2 = (String) comboBox2.getSelectedItem();
            String selected3 = (String) comboBox3.getSelectedItem();

            if (selected1.equals("Taxi")){
                 t = new Taxi();
            } else{
                t = new Bus();
            }

            if (selected2.equals("Sea Cruise")){
                act = new SeaCruise();
            } else{
                act = new SkyDiving();
            }

            if (selected3.equals("Hotel")){
                a = new Hotel();
            }else{
                a = new Motel();
            }

            packagePrice = t.getPrice() + act.getPrice() + a.getPrice();

            // System.out.println("The Total price of services: "+packagePrice);

            servicesArrayList.clear();
            servicesArrayList.add(t);
            servicesArrayList.add(act);
            servicesArrayList.add(a);

            if (userWantsLuxuryTaxiOrNot) {
                baseService1 = new LuxuryTaxi(baseService1);
                packagePrice += baseService1.getPrice();
                servicesArrayList.add(baseService1);
            }

            if (userWantsAdventureSeaCruiseOrNot) {
                baseService2 = new AdventureSeaCruise(baseService2);
                packagePrice += baseService2.getPrice();
                servicesArrayList.add(baseService2);
            }

            if (userWantsLuxuryHotelOrNot) {
                baseService3 = new LuxuryHotel(baseService3);
                packagePrice += baseService3.getPrice();
                servicesArrayList.add(baseService3);
            }


            DisplayTotalPrice(packagePrice);
        } else{

            // IF PACKAGE SELECTED:
            if (package1RadioBTN.isSelected()){


                // BEFORE BUILDER PATTERN
                // PackageOffer offer1 = new Offer1();

                PackageBuilder offer1 = new Offer1();
                offer1.createPackage();

                DisplayTotalPrice(offer1.getP().getTotalPricee());

                // adding the chosen services to the arraylist
                servicesArrayList.clear();
                servicesArrayList.add(offer1.getP().getT());
                servicesArrayList.add(offer1.getP().getAct());
                servicesArrayList.add(offer1.getP().getA());

            }else if(package2RadioBTN.isSelected()){

                PackageBuilder offer2 = new Offer2();
                offer2.createPackage();

                DisplayTotalPrice(offer2.getP().getTotalPricee());
                // adding the chosen services to the arraylist
                servicesArrayList.clear();
                servicesArrayList.add(offer2.getP().getT());
                servicesArrayList.add(offer2.getP().getAct());
                servicesArrayList.add(offer2.getP().getA());

            }else if(package3RadioBTN.isSelected()){

                PackageBuilder offer3 = new Offer3();
                offer3.createPackage();

                DisplayTotalPrice(offer3.getP().getTotalPricee());
                // adding the chosen services to the arraylist
                servicesArrayList.clear();
                servicesArrayList.add(offer3.getP().getT());
                servicesArrayList.add(offer3.getP().getAct());
                servicesArrayList.add(offer3.getP().getA());
            }
        }
        return servicesArrayList;

    }

    private void DisplayTotalPrice(int packagePrice) {
        int beforeDiscount = packagePrice;

        if (comboBox.getSelectedItem().equals("Student")){
            packagePrice = (int) (packagePrice * 0.9);
        }else if (comboBox.getSelectedItem().equals("Business")){
            packagePrice = (int) (packagePrice * 1);
        }else if (comboBox.getSelectedItem().equals("Retired")){
            packagePrice = (int) (packagePrice * 0.8);
        }else  if (comboBox.getSelectedItem().equals("Employee")){
            packagePrice = (int) (packagePrice * 0.7);
        }

        totalPriceLBL.setText("Total Price Before\n Discount: " +beforeDiscount +"\nTotal Price\n After Discount: \n" +packagePrice + " $");

    }

    public void SaveReservationToDisk() throws ParseException, IOException {
        File file = new File("C:/Users/admin/OneDrive/Desktop/Deneme Proje/myfile.dat");
        String phoneNumber = cusPhone.getText();
        if (!file.exists()) {

            // create a new file
            file.createNewFile();
            SaveCurrentReservationtoNewFile(phoneNumber, file);

        } else {

            try{

                TreeMap<String, Reservation> newMaptoSave = new TreeMap<>();

                InputStream is = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(is);

                Reservation reservation = new Reservation(CreateCustomer(),GetChoosenServices(),paymentStrategy);

                TreeMap<String,Reservation> mapInFile = (TreeMap<String,Reservation>)ois.readObject();
                ois.close();
                is.close();

                // Get old map
                for(Map.Entry<String,Reservation> m :mapInFile.entrySet()){
                    newMaptoSave.put(m.getKey(), m.getValue());
                }

                // Adding new Reservation to Map
                newMaptoSave.put(phoneNumber, reservation);

                OutputStream os = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(newMaptoSave);
                oos.flush();
                oos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

   private void SaveCurrentReservationtoNewFile(String phoneNumber, File file) throws IOException {

        // Memento Design Pattern Implementation

        Originator originator = new Originator(CreateCustomer(),
        GetChoosenServices(), new CareTaker(), paymentStrategy);
        
        originator.createReservation(phoneNumber);
   }

    public void SearchCustomerbyMobileNb(){

        File file = new File("C:/Users/admin/OneDrive/Desktop/Deneme Proje/myfile.dat");

        try{
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);

            TreeMap<String,Reservation> mapInFile = (TreeMap<String,Reservation>)ois.readObject();
            ois.close();
            is.close();

            // Displaying Reservation Detials
            Reservation reservation_found = mapInFile.get(searchField.getText());
            System.out.println(""+reservation_found.toString());
            reservationDetailsArea.setText(reservation_found.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void CustomizePanel8() {
        

        // Chat Panel
        JPanel p8 = new JPanel();
        TitledBorder titledBorder8 = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "  Chat  ",
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, myFont,
                myColor);
        p8.setBorder(titledBorder8);


        userChatTextArea = new JTextArea();
        userChatTextArea.setEditable(false);
        JScrollPane userChatScrollPane = new JScrollPane(userChatTextArea);

        supportChatTextArea = new JTextArea();
        supportChatTextArea.setEditable(false);
        JScrollPane supportChatScrollPane = new JScrollPane(supportChatTextArea);

        chatInputField = new JTextField();
        userSendChatButton = new JButton("Send as User");
        supportSendChatButton = new JButton("Send as Support");

        userSendChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = chatInputField.getText();
                if (!message.isEmpty()) {
                    userChatSubject.notifyObservers(message, "User");
                    chatInputField.setText("");
                }
            }
        });

        supportSendChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = chatInputField.getText();
                if (!message.isEmpty()) {
                    supportChatSubject.notifyObservers(message, "Support");
                    chatInputField.setText("");
                }
            }
        });

        p8.add(userChatScrollPane);
        p8.add(supportChatScrollPane);
        p8.add(chatInputField);
        p8.add(userSendChatButton);
        p8.add(supportSendChatButton);

        p8.setBounds(990, 15, 450, 600);
        p8.setLayout(new GridLayout(3, 2));

        setLayout(null);
        add(p8);
    }

    @Override
    public void update(String message, String role) {
        if ("User".equals(role)) {
            userChatTextArea.append("User: " + message + "\n");
        } else if ("Support".equals(role)) {
            supportChatTextArea.append("Customer Support: " + message + "\n");
        }
    }

    public static void main(String arg[]) throws ParseException {
        MainScreen mainFrame = new MainScreen();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Tourist Agency System");
        mainFrame.setBounds(0,0,1920  ,1080);
    }
}
    