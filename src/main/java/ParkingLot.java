import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private final int fixedCapacity;
    private int countOfCar;
    private Set<Car> set = new HashSet<>();

    public ParkingLot(int fixedCapacity){
        this.fixedCapacity = fixedCapacity;
        this.countOfCar = 0;
    }

    public int fixedCapacity(){
        return fixedCapacity;
    }

    public void parkCar(Car car) throws ParkingLotFullException, CarAlreadyParkedException {
        if(countOfCar >= fixedCapacity){
            throw new ParkingLotFullException();
        }
        else if(set.contains(car)){
            throw  new CarAlreadyParkedException();
        }

        set.add(car);
        countOfCar++;
    }

    public boolean hasSpace() {
        return countOfCar < fixedCapacity;
    }
}
