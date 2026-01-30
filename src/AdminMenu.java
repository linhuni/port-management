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

            int c = readInt();
            switch (c) {
                case 1 -> portsCrudMenu();
                case 2 -> System.out.println("[TODO] Vehicles CRUD");
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

            int c = readInt();
            switch (c) {
                case 1 -> createPort();
                case 2 -> viewPorts();
                case 3 -> findPortById();
                case 4 -> deletePort();
                case 5 -> updatePort();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    public int readInt(){
        while(true){
            String input = sc.nextLine().trim();
            try{
                return Integer.parseInt(input);
            }catch (NumberFormatException e){
                System.out.println("Invalid number");
            }

        }
    }

}
