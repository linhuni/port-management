public class reeferTruck extends Truck {


    public reeferTruck(String name, float currentFuel, int carryingCap, float fuelCap, Port currentPort) {
        super(name, currentFuel, carryingCap, fuelCap, currentPort);
        this.id = genID();
    }

    @Override
    public boolean canCarried(Container container) {
        return container instanceof refrigerated;
    }
}
