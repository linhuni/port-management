
import java.util.Random;

public class Ship extends Vehicle{


    public Ship(String name, float currentFuel, int carryingCap, float fuelCap, Port currentPort) {
        super(name, currentFuel, carryingCap, fuelCap, currentPort);
        this.id = genID();
    }

    @Override
    public boolean canCarried(Container container) {
        return true;
    }

    @Override
    public String genID() {
        Random rand = new Random();
        int number = 1000 + rand.nextInt(9000);
        return "sh-"+number;
    }

}
