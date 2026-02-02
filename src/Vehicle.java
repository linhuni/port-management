
import java.util.ArrayList;
import java.util.Random;

abstract public class Vehicle {
    protected VehicleStatus status;
    protected String id;
    private String name;
    private float currentFuel;
    private int carryingCap;
    private float fuelCap;
    private Port currentPort;
    protected int numCons;
    protected ArrayList<Container> containers;

    public Vehicle(String name, float currentFuel, int carryingCap, float fuelCap, Port currentPort) {
        this.id = genID();
        this.name = name;
        this.currentFuel = currentFuel;
        this.carryingCap = carryingCap;
        this.fuelCap = fuelCap;
        this.currentPort = currentPort;
        this.containers = new ArrayList<>();

    }

    //check the container's type
    public abstract boolean canCarried(Container container);

    //boolean shows true for successful carry container, false for unsuccessful
    public boolean loadContainer(Container container) {
        if (canCarried(container) && !containers.contains(container)) {
            containers.add(container);
            this.numCons = containers.size();
            return true;
        } else {
            return false;
        }
    }

    //boolean shows true for successfully unload containers from vehicle
    public boolean unloadContainer(Container container) {
        if (containers.contains(container)) {
            containers.remove(container);
            this.numCons = containers.size();
            return true;
        } else {
            return false;
        }
    }

    //Load container from port to vehicle --> the containers in port
    // will increase and in vehicle will decrease
    public boolean exportCon(Port p, Container c) {
        if (!containers.contains(c)) return false;
        if (this.currentPort != p) return false;
        containers.remove(c);
        this.numCons = containers.size();
        return p.loadCon(c);
    }

    //Load container from vehicle to port ---> the containers in vehicle
    // will increase and in port will decrease
    public boolean importCon(Port p, Container c) {
        if (p.getContainers().contains(c)) return false;
        if (containers.contains(c)) return false;
        containers.add(c);
        this.numCons = containers.size();
        return p.unloadCon(c);
    }


    public abstract String genID();

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public void setCurrentPort(Port port) {
        this.currentPort = port;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    //Check if the vehicle is not in port
    public boolean isTransit() {
        return status == VehicleStatus.IN_TRANSIT;
    }

    //Check if the vehicle is in port
    public boolean isAtPort() {
        return status == VehicleStatus.AT_PORT;
    }

    public String getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentFuel(float currentFuel) {
        this.currentFuel = currentFuel;
    }

    @Override
    public String toString() {
        String portInfo = (currentPort == null)
                ? "null"
                : currentPort.getId() + " (" + currentPort.getName() + ")";

        return "Vehicle{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", currentFuel=" + currentFuel +
                ", carryingCap=" + carryingCap +
                ", fuelCap=" + fuelCap +
                ", currentPort=" + portInfo +
                ", containersCount=" + (containers == null ? 0 : containers.size()) +
                '}';
    }

}
