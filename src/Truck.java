
import java.util.Random;

abstract public class Truck extends Vehicle{

    public Truck(String name, float currentFuel, int carryingCap, float fuelCap, Port currentPort) {
        super(name, currentFuel, carryingCap, fuelCap, currentPort);
        this.id = genID();
    }

    public String genID(){
         Random rand = new Random();
         int number = 1000 + rand.nextInt(9000);
         return "t-"+number;
    }

    public boolean loadContainer(Container container) {
        if (canCarried(container)&& !containers.contains(container)) {
            containers.add(container);
            this.numCons = containers.size();
            return true;
        }else {
            return false;
        }
    }

}

