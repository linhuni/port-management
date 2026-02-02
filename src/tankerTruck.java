public class tankerTruck extends Truck {


    public tankerTruck(String name, float currentFuel, int carryingCap, float fuelCap, Port currentPort) {
        super(name, currentFuel, carryingCap, fuelCap, currentPort);

    }

    @Override
    //check the container is liquid
    public boolean canCarried(Container container) {
        return container instanceof liquidContainer;
    }

}
