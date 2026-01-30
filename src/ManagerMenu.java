import java.util.Scanner;

public class ManagerMenu {
    public final DataStorage db;
    public final Scanner sc;

    public ManagerMenu( Scanner sc, DataStorage db) {
        this.db = db;
        this.sc = sc;
    }
}
