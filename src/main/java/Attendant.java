import java.util.HashSet;
import java.util.Set;

public class Attendant {
    private Set<ParkingLot> set;
    public Attendant(){
        set = new HashSet<>();
    }
    public void parkTheCar(Car car) throws ParkingLotFullException, CarAlreadyParkedException {
        for(ParkingLot parkingLot: set) {
            if(parkingLot.hasSpace()){
                parkingLot.park(car);
                return;
            }
        }
        throw new ParkingLotFullException();
    }

    public void unParkTheCar(Car car) throws CarIsNotParkedException {
        for(ParkingLot parkingLot: set) {
            if(parkingLot.removeIfParked(car)){
                return ;
            }
        }
        throw new CarIsNotParkedException();
    }

    public void register(ParkingLot parkingLot) {
        set.add(parkingLot);
    }
}
