import java.util.Scanner;



public class AdminMenu {

    public final Scanner sc;
    public final DataStorage db;

    public AdminMenu(Scanner sc, DataStorage db) {
        this.sc = sc;
        this.db = db;
    }

    public void run() {
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. Ports CRUD");
            System.out.println("2. Vehicles CRUD");
            System.out.println("3. Containers CRUD");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            int c = portSystem.readInt();
            switch (c) {
                case 1 -> portsCrudMenu();
                case 2 -> vehiclesCrudMenu();
                case 3 -> System.out.println("[TODO] Containers CRUD");
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public void createPort(){

        System.out.println("Port name: ");
        String name = sc.nextLine().trim();

        System.out.println("Latitude: ");
        double latitude = sc.nextDouble();

        System.out.println("Longitude: ");
        double longitude = sc.nextDouble();

        System.out.println("Storing capability: ");
        int cap = sc.nextInt();

        System.out.println("Allow landing? (true/false):");
        boolean landing = Boolean.parseBoolean(sc.nextLine().trim());

        Port p = new Port(name, latitude, longitude, cap, landing);

        db.ports.add(p);
        System.out.println("Created port:");
        System.out.println(p);

    }

    public void viewPorts() {
        if (db.ports.isEmpty()) {
            System.out.println("No ports available.");
            return;
        }

        System.out.println("=== PORT LIST ===");
        for (Port port : db.ports) {
            System.out.println(port);
        }
    }

    public void updatePort(){
        System.out.println("Port id");
        String id = sc.nextLine().trim();
        for (Port p: db.ports) {
            if(p.getId().equals(id)){
                System.out.print("New name (blank to keep): ");
                String name = sc.nextLine().trim();
                if(!name.isEmpty()){
                    p.setName(name);
                }

                System.out.print("New latitude (-999 to keep): ");
                double latitude = Double.parseDouble(sc.nextLine().trim());
                if(latitude != -999){
                    p.setLatitude(latitude);
                }

                System.out.print("New longitude (-999 to keep): ");
                double longitude = Double.parseDouble(sc.nextLine().trim());
                if(longitude != -999){
                    p.setLongitude(longitude);
                }

                System.out.print("New storing capacity (-999 to keep): ");
                int cap = Integer.parseInt(sc.nextLine().trim());
                if(cap != 999){
                    p.setStroringCap(cap);
                }

                System.out.print("Allow landing? (true/false, blank to keep): ");
                String landing = sc.nextLine().trim();
                if (!landing.isEmpty()) {
                    p.setLanding(Boolean.parseBoolean(landing));
                }

                System.out.println("Updated port:");
                System.out.println(p);
            }
        }
        System.out.println("Port not found.");
    }
    public void deletePort(){
        System.out.println("Port id");
        String id = sc.nextLine().trim();
        if(!db.ports.isEmpty()){
            for (Port port: db.ports) {
                if(port.getId().equals(id)){
                    db.ports.remove(port);
                }
            }
            System.out.println("Successfully deleted");
        }else {
            System.out.println("Port is not created yet");
        }
    }
    public Port findPortById(){
        System.out.println("Port id");
        String id = sc.nextLine().trim();
        for (Port p: db.ports) {
            if(p.getId().equals(id)){
                return p;
            }
        }
        System.out.println("Port not found.");
        return null;
    }
    public void portsCrudMenu() {
        while (true) {
            System.out.println("\n=== PORTS CRUD ===");
            System.out.println("1. Create Port");
            System.out.println("2. View Ports");
            System.out.println("3. Update Port");
            System.out.println("4. Delete Port");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            int c = portSystem.readInt();
            switch (c) {
                case 1 -> createPort();
                case 2 -> viewPorts();
                case 3 -> deletePort();
                case 4 -> updatePort();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public  void vehiclesCrudMenu(){
        while (true) {
            System.out.println("\n=== VEHICLES CRUD ===");
            System.out.println("1. Create Vehicle");
            System.out.println("2. View Vehicles");
            System.out.println("3. Update Vehicle");
            System.out.println("4. Delete Vehicle");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            int c = portSystem.readInt();
            switch (c) {
                case 1 -> createVehicle();
                case 2 -> viewVehicles();
                case 3 -> deleteVehicle();
                case 4 -> updateVehicle();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }



    private void deleteVehicle() {
    }

    private void viewVehicles() {
    }

    private void createVehicle() {

        System.out.println("Vehicle name: ");
        String name = sc.nextLine().trim();

        System.out.println("Current fuel: ");
        float currentFuel = Float.parseFloat(sc.nextLine());

        System.out.println("Carrying capability: ");
        int carryCap = Integer.parseInt(sc.nextLine());

        System.out.println("Fuel capability: ");
        float fuelCap = Float.parseFloat(sc.nextLine());

        System.out.println("Current port ( Port id): ");
        String portID = sc.nextLine().trim();
        Port currentP = findCurrentPort(portID);
        if (currentP == null) {
            System.out.println("Port not found. Cancel create vehicle.");
            return;
        }

        System.out.println("Truck or Ship ( T for truck and S for ship ): ");
        String type = sc.nextLine().trim().toUpperCase();
        if(type.equals("T")){
            createTruckByType(name, currentFuel, carryCap,  fuelCap, currentP);}
        else if(type.equals("S")){
            Ship ship = new Ship(name, currentFuel,carryCap,fuelCap, currentP);
        }else {
            System.out.println("Invalid type. Please enter T or S.");
        }

    }

    private void updateVehicle() {
        System.out.println("Vehicle id");
        String Veid = sc.nextLine().trim();
        for (Vehicle v: db.vehicles) {
            if(v.getId().equals(Veid)){
                System.out.print("New name (blank to keep): ");
                String name = sc.nextLine().trim();
                if(!name.isEmpty()){
                    v.setName(name);
                }

                System.out.print("Current fuel (-999 to keep): ");
                float currentFuel = Float.parseFloat(sc.nextLine());
                if(currentFuel != -999){
                    v.setCurrentFuel(currentFuel);
                }

                System.out.print("Carrying cap (-999 to keep): ");
                int carryCap = Integer.parseInt(sc.nextLine());
                if(currentFuel != -999){
                    v.setCurrentFuel(currentFuel);
                }


                System.out.println("Fuel capability: ");
                float fuelCap = Float.parseFloat(sc.nextLine());

                System.out.println("Current port ( Port id): ");
                String portID = sc.nextLine().trim();
                Port currentP = findCurrentPort(portID);
                if (currentP == null) {
                    System.out.println("Port not found. Cancel create vehicle.");
                    return;
                }

            }
        }
        System.out.println("Port not found.");

    }

    private void createTruckByType(String name, float currentFuel, int carryCap, float fuelCap, Port currentP) {
        while(true){
            System.out.println("1. Create basic truck");
            System.out.println("2. Create reefer truck");
            System.out.println("3. Create tanker truck");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            int c = portSystem.readInt();

            switch (c){
                case 1 -> {Truck basicTruck= new basicTruck(name, currentFuel, carryCap, fuelCap, currentP);}
                case 2 -> {Truck tankerTruck= new tankerTruck(name, currentFuel, carryCap, fuelCap, currentP);}
                case 3 -> {Truck reeferTruck= new reeferTruck(name, currentFuel, carryCap, fuelCap, currentP);}
                case 0 -> {return;}
                default -> System.out.println("Invalid choice.");
            }

        }
    }


    public Port findCurrentPort(String id){;
        for (Port p: db.ports) {
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

}
