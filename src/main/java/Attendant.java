public class Attendant {
    private ParkingLot parkingLot;
    public Attendant(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }
    public void parkTheCar(Car car) throws ParkingLotFullException, CarAlreadyParkedException {
        parkingLot.park(car);
    }

    public void unParkTheCar(Car car) throws CarIsNotParkedException {
        parkingLot.unPark(car);
    }
}
