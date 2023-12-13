import org.example.Observers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AttendantTest {

    @Test
    public void attendantShouldBeAbleToParkTheCar() throws ParkingLotFullException, CarAlreadyParkedException {
        Observers owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Attendant attendant = new Attendant(parkingLot);
        Car car = new Car();
        attendant.parkTheCar(car);
    }

    @Test
    public void attendantShouldBeAbleToUnParkTheCar() throws ParkingLotFullException, CarAlreadyParkedException, CarIsNotParkedException {
        Observers owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Attendant attendant = new Attendant(parkingLot);
        Car car = new Car();
        attendant.parkTheCar(car);
        attendant.unParkTheCar(car);
    }
}
