import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ParkingTest {
    @Test
    public void shouldReturnFixedCapacity20IfCapacity20() {
        ParkingLot parkingLot = new ParkingLot(20);

        assertTrue(parkingLot.hasSpace());
    }
    @Test
    public void shouldReturnFixedCapacity30IfCapacity30() {
        ParkingLot parkingLot = new ParkingLot(30);
        int expectedCapacity = 30;

        assertEquals(expectedCapacity,parkingLot.fixedCapacity());
    }

    @Test
    public void shouldBeAbleToParkCar() throws ParkingLotFullException, CarAlreadyParkedException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(30);
        parkingLot.parkCar(car);
    }

    @Test
    public void shouldNotBeAbleToParkCarWhenParkingLotIsFull() throws ParkingLotFullException, CarAlreadyParkedException {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        assertThrows(ParkingLotFullException.class, () -> parkingLot.parkCar(car3));
    }

    @Test
    public void shouldNotBeAbleToParkTheSameCarAgain() throws CarAlreadyParkedException, ParkingLotFullException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.parkCar(car);
        assertThrows(CarAlreadyParkedException.class, () -> parkingLot.parkCar(car));
    }
}
