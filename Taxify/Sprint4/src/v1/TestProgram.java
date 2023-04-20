package v1;

import java.util.ArrayList;
import java.util.List;

public class TestProgram {

    public static void main(String[] args) {
        List<IUser> users = new ArrayList<>();
        List<IVehicle> vehicles = new ArrayList<>();


        IUser u1 = new User(1,"M","B", ApplicationLibrary.randomLocation());
        IUser u2 = new User(2,"G","A",ApplicationLibrary.randomLocation());
        IUser u3 = new User(3,"H","W",ApplicationLibrary.randomLocation());
        IUser u4 = new User(4,"N","Q",ApplicationLibrary.randomLocation());
        IUser u5 = new User(5,"W","R",ApplicationLibrary.randomLocation());
        IUser u6 = new User(6,"Q","E",ApplicationLibrary.randomLocation());
        IUser u7 = new User(7,"D","Y",ApplicationLibrary.randomLocation());
        IUser u8 = new User(8,"A","T",ApplicationLibrary.randomLocation());
        IUser u9 = new User(9,"L","U",ApplicationLibrary.randomLocation());
        IUser u10 = new User(10,"P","I",ApplicationLibrary.randomLocation());
        IUser u11 = new User(11,"I","O",ApplicationLibrary.randomLocation());
        IUser u12 = new User(12,"U","P",ApplicationLibrary.randomLocation());
        IUser u13 = new User(13,"X","L",ApplicationLibrary.randomLocation());
        IUser u14 = new User(14,"C","K",ApplicationLibrary.randomLocation());
        IUser u15 = new User(15,"J","N",ApplicationLibrary.randomLocation());

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        users.add(u6);
        users.add(u7);
        users.add(u8);
        users.add(u9);
        users.add(u10);
        users.add(u11);
        users.add(u12);
        users.add(u13);
        users.add(u14);
        users.add(u15);


        IVehicle v1 = new Taxi(1, ApplicationLibrary.randomLocation());
        IVehicle v2 = new Taxi(2, ApplicationLibrary.randomLocation());
        IVehicle v3 = new Shuttle(3, ApplicationLibrary.randomLocation());
        IVehicle v4 = new Taxi(4, ApplicationLibrary.randomLocation());
        IVehicle v5 = new Shuttle(5, ApplicationLibrary.randomLocation());
        IVehicle v6 = new Shuttle(6, ApplicationLibrary.randomLocation());
        IVehicle v7 = new Taxi(7, ApplicationLibrary.randomLocation());
        IVehicle v8 = new Taxi(8, ApplicationLibrary.randomLocation());
        IVehicle v9 = new Shuttle(9, ApplicationLibrary.randomLocation());
        IVehicle v10 = new Taxi(10, ApplicationLibrary.randomLocation());

        vehicles.add(v1);
        vehicles.add(v2);
        vehicles.add(v3);
        vehicles.add(v4);
        vehicles.add(v5);
        vehicles.add(v6);
        vehicles.add(v7);
        vehicles.add(v8);
        vehicles.add(v9);
        vehicles.add(v10);

        // Instantiate the Taxi company and the Application simulator
        TaxiCompany taxify = new TaxiCompany("Taxify", users, vehicles);
        ApplicationSimulator app = new ApplicationSimulator(taxify, users, vehicles);

        // add an observer to taxify simulator
        taxify.addObserver(app);

        // status of the application
        app.show();

        // simulates requests of service
        for(int i = 0; i < 5; i++) {
            app.requestService();
        }
        do {
            // we show the status of the simulation

            app.show();

            System.out.println("\n");

            app.update();


            if (ApplicationLibrary.rand() % 4 == 0) {
                app.requestService();
                app.requestSharedService();
            }

        } while (app.getTotalServices() != 0);

        // after the end of the service show statistics
        app.showStatistics();


    } // method main



} // class TestProgram