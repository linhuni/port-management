import java.util.ArrayList;
import java.util.Scanner;

public class portSystem {
    public static final Scanner sc = new Scanner(System.in);
    public final DataStorage db = new DataStorage();
    private Account currentUser;


    public Account SignUp(){
        //set and check username
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        for (Account a : db.accounts) {
            if(a.getUsername().equals(username)){
                System.out.println("User name is existing");
                return null;
            }
        }
        Account account = new Account(username);
        //set and check password
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        if(!account.setPassword(password)){
            System.out.println("password is invalid");
            return null;
        }
        //assign role ADMIN for first account
        if(db.accounts.isEmpty()) {
            account.setAccountRole(AccountRole.ADMIN);
            System.out.println("First account created → ADMIN role assigned");
        }else{
            account.setAccountRole(AccountRole.MANAGER);}
        System.out.println("Account created successfully!");
        db.accounts.add(account);
        return account;

    }

    public Account Login(){

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        for (Account acc: db.accounts) {
            if(acc.getPassword().equals(password) && acc.getUsername().equals(username)){
                System.out.println("Successfully log in");
                return acc;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public void viewProfile(String username){
        for (Account acc: db.accounts) {
            if(acc.getUsername().equals(username)){
                System.out.println(acc);
            }
        }
    }

    public void run(){
        while(true){
            System.out.println("\n=== PORT SYSTEM ===");
            System.out.println("1. Sign up (Register)");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = readInt();
            switch (choice){
                case 1:
                    SignUp();
                    break;
                case 2:
                    currentUser = Login();
                    if(currentUser != null){
                        System.out.println("Welcome" + currentUser);
                        menuLoop();
                        currentUser = null;
                    }
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Invalid choice");

            }
        }
    }
    private void menuLoop(){
        while(true){
            System.out.println("Choice");
            int choice = readInt();
            if(choice == 0){
                System.out.println("Log out");
                return;
            }
            if (currentUser.getAccountRole() == AccountRole.ADMIN) {
                new AdminMenu(sc, db).run();
            }else{
                new ManagerMenu(sc, db).run();
            }
        }
    }

    //For user to enter int
    public static int readInt(){
        while(true){
            String input = sc.nextLine().trim();
            try{
                return Integer.parseInt(input);
            }catch (NumberFormatException e){
                System.out.println("Invalid number");
            }

        }
    }


    private void handleManagerChoice(int choice) {
        switch (choice) {
            case 1 -> System.out.println("[MANAGER] Containers CRUD (TODO)");
            case 2 -> System.out.println("[MANAGER] Load/Unload container (TODO)");
            default -> System.out.println("Invalid choice for MANAGER.");
        }
    }





}



