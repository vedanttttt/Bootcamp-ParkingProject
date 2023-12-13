import org.example.Observers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AttendantTest {

    @Test
    public void attendantShouldBeAbleToParkTheCar() throws ParkingLotFullException, CarAlreadyParkedException {
        Observers owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Attendant attendant = new Attendant();
        attendant.register(parkingLot);
        Car car = new Car();
        attendant.parkTheCar(car);
    }

    @Test
    public void attendantShouldBeAbleToUnParkTheCar() throws ParkingLotFullException, CarAlreadyParkedException, CarIsNotParkedException {
        Observers owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Attendant attendant = new Attendant();
        attendant.register(parkingLot);
        Car car = new Car();
        attendant.parkTheCar(car);
        attendant.unParkTheCar(car);
    }

    @Test
    public void attendantShouldBeAbleToParkTheCarWithMultipleParkingLots() throws ParkingLotFullException, CarAlreadyParkedException, CarIsNotParkedException {
        Observers owner1 = new Owner();
        Observers owner2 = new Owner();
        ParkingLot parkingLot1 = new ParkingLot(2, owner1);
        ParkingLot parkingLot2 = new ParkingLot(3, owner2);
        Attendant attendant = new Attendant();
        attendant.register(parkingLot1);
        attendant.register(parkingLot2);
        Car car = new Car();
        attendant.parkTheCar(car);
    }
}
