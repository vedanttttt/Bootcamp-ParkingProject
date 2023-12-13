import org.example.Observers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


public class ParkingTest {
    @Test
    public void shouldReturnFixedCapacity20IfCapacity20() {
        ParkingLot parkingLot = new ParkingLot(20,new Owner());

        assertTrue(parkingLot.hasSpace());
    }
    @Test
    public void shouldReturnFixedCapacity30IfCapacity30() {
        ParkingLot parkingLot = new ParkingLot(30, new Owner());
        int expectedCapacity = 30;

        assertEquals(expectedCapacity,parkingLot.fixedCapacity());
    }

    @Test
    public void shouldBeAbleToParkCar() throws ParkingLotFullException, CarAlreadyParkedException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(30,new Owner());
        parkingLot.park(car);
    }

    @Test
    public void shouldNotBeAbleToParkCarWhenParkingLotIsFull() throws ParkingLotFullException, CarAlreadyParkedException {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot = new ParkingLot(2,new Owner());
        parkingLot.park(car1);
        parkingLot.park(car2);
        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(car3));
    }

    @Test
    public void shouldNotBeAbleToParkTheSameCarAgain() throws CarAlreadyParkedException, ParkingLotFullException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2,new Owner());
        parkingLot.park(car);
        assertThrows(CarAlreadyParkedException.class, () -> parkingLot.park(car));
    }

    @Test
    public void shouldBeAbleToUnParkTheCar() throws CarAlreadyParkedException, ParkingLotFullException, CarIsNotParkedException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2,new Owner());
        parkingLot.park(car);
        parkingLot.unPark(car);
    }

    @Test
    public void shouldNotBeAbleToUnParkTheCarIfNotParked() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2,new Owner());
        assertThrows(CarIsNotParkedException.class, () -> parkingLot.unPark(car));
    }

    @Test
    public void shouldNotBeAbleToUnParkTheCarIfCapacityIs0()  {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(0,new Owner());
        assertThrows(CarIsNotParkedException.class, () -> parkingLot.unPark(car));
    }

    @Test
    public void shouldBeAbleToKnowIfCarIsParked() throws ParkingLotFullException, CarAlreadyParkedException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2,new Owner());
        parkingLot.park(car);
        assertTrue(parkingLot.isCarParked(car));
    }

    @Test
    public void shouldBeAbleToKnowIfCarIsNotParked(){
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2,new Owner());
        assertFalse(parkingLot.isCarParked(car));
    }

    @Test
    public void OwnerShouldBeNotifiedWhenParkingIsFull() throws ParkingLotFullException, CarAlreadyParkedException {
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLot.park(car1);
        parkingLot.park(car2);


        Mockito.verify(owner, Mockito.times(1)).notifyWhenFull();
    }

    @Test
    public void OwnerShouldBeNotifiedWhenParkingIsAvailableForParkingAgain() throws ParkingLotFullException, CarAlreadyParkedException, CarIsNotParkedException {
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLot.park(car1);
        parkingLot.park(car2);
        Mockito.verify(owner, Mockito.times(1)).notifyWhenFull();
        parkingLot.unPark(car2);
        Mockito.verify(owner,Mockito.times(1)).notifyWhenAvailable();
    }

    @Test
    public void OwnerShouldNotBeNotifiedWhenParkingIsAvailableForParkingIfItWasNeverFull() throws ParkingLotFullException, CarAlreadyParkedException, CarIsNotParkedException {
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Car car1 = new Car();
        parkingLot.park(car1);
        Mockito.verify(owner, Mockito.times(0)).notifyWhenFull();
        parkingLot.unPark(car1);
        Mockito.verify(owner,Mockito.times(0)).notifyWhenAvailable();
    }

    @Test
    public void copShouldBeNotifiedWhenParkingIsFull() throws ParkingLotFullException, CarAlreadyParkedException {
        Observers owner = Mockito.mock(Observers.class);
        Observers trafficCop = Mockito.mock(Observers.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        parkingLot.register(trafficCop);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLot.park(car1);
        parkingLot.park(car2);


        Mockito.verify(owner, Mockito.times(1)).notifyWhenFull();
        Mockito.verify(trafficCop, Mockito.times(1)).notifyWhenFull();
    }

    @Test
    public void TrafficCopShouldBeNotifiedWhenParkingIsAvailableForParkingAgain() throws ParkingLotFullException, CarAlreadyParkedException, CarIsNotParkedException {
        Observers owner = Mockito.mock(Observers.class);
        Observers trafficCop = Mockito.mock(Observers.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        parkingLot.register(trafficCop);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLot.park(car1);
        parkingLot.park(car2);
        Mockito.verify(owner, Mockito.times(1)).notifyWhenFull();
        Mockito.verify(trafficCop, Mockito.times(1)).notifyWhenFull();
        parkingLot.unPark(car2);
        Mockito.verify(owner,Mockito.times(1)).notifyWhenAvailable();
        Mockito.verify(trafficCop,Mockito.times(1)).notifyWhenAvailable();
    }

    @Test
    public void trafficCopShouldNotBeNotifiedWhenParkingIsAvailableForParkingIfItWasNeverFull() throws ParkingLotFullException, CarAlreadyParkedException, CarIsNotParkedException {
        Observers owner = Mockito.mock(Observers.class);
        Observers trafficCop = Mockito.mock(Observers.class);
        ParkingLot parkingLot = new ParkingLot(2, owner);
        parkingLot.register(trafficCop);
        Car car1 = new Car();
        parkingLot.park(car1);
        Mockito.verify(owner, Mockito.times(0)).notifyWhenFull();
        Mockito.verify(trafficCop, Mockito.times(0)).notifyWhenFull();
        parkingLot.unPark(car1);
        Mockito.verify(owner,Mockito.times(0)).notifyWhenAvailable();
        Mockito.verify(trafficCop,Mockito.times(0)).notifyWhenAvailable();
    }

}
