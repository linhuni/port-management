import java.util.ArrayList;
import java.time.LocalDateTime;



//Trip allows vehicle to go to port
public class Trip {
    private Vehicle vehicle;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Port from;
    private Port to;

    private TripStatus status = TripStatus.PLANNED;

    public Trip(Port from, Port to, Vehicle vehicle) {
        this.from = from;
        this.to = to;
        this.vehicle = vehicle;
    }

    //Move one vehicle from this port to that port
    public boolean start() {
        if (from == null || to == null || vehicle == null) return false;
        if (!from.isLanding() || !to.isLanding()) return false;

        switch (status) {
            case PLANNED:
                if (vehicle.getCurrentPort() != from) return false;

                if (!from.removeVe(vehicle)) return false;

                vehicle.setCurrentPort(null);
                status = TripStatus.ONGOING;
                departureTime = LocalDateTime.now();
                return true;

            default:
                return false;
        }
    }

    public boolean finish() {
        if (from == null || to == null || vehicle == null) return false;
        if (!from.isLanding() || !to.isLanding()) return false;

        switch (status) {
            case ONGOING:
                vehicle.setCurrentPort(to);

                if (!to.addVehicle(vehicle)) {
                    vehicle.setCurrentPort(null); // rollback
                    return false;
                }

                status = TripStatus.COMPLETED;
                arrivalTime = LocalDateTime.now();
                return true;

            default:
                return false;
        }
    }




    public TripStatus getStatus() {
        return status;
    }
}



