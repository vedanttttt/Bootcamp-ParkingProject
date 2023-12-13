import org.example.Observers;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private final int fixedCapacity;
    private Set<Observers> observers = new HashSet<>();
    private Set<Car> set = new HashSet<>();

    public ParkingLot(int fixedCapacity, Observers owner){
        this.fixedCapacity = fixedCapacity;
        this.observers.add(owner);
    }

    public boolean removeIfParked(Car car){
        if(set.contains(car)){
            set.remove(car);
            return true;
        }
        return false;
    }

    public int fixedCapacity(){
        return fixedCapacity;
    }

    public void park(Car car) throws ParkingLotFullException, CarAlreadyParkedException {
        if(set.size() == fixedCapacity){
            throw new ParkingLotFullException();
        }
        else if(set.contains(car)){
            throw  new CarAlreadyParkedException();
        }

        set.add(car);
        if(set.size() == fixedCapacity) observers.forEach(Observers::notifyWhenFull);
    }

    public boolean hasSpace() {
        return set.size() < fixedCapacity;
    }

    public void unPark(Car car) throws CarIsNotParkedException {
        if (removeIfParked(car)) {
            if(set.size()==fixedCapacity-1){
                observers.forEach(Observers::notifyWhenAvailable);
            }
            return ;
        }
        throw new CarIsNotParkedException();
    }

    public boolean isCarParked(Car car) {
        return set.contains(car);
    }

    public void register(Observers trafficCop) {
        observers.add(trafficCop);
    }

//    public Object isParkingFull() {
//        return set.size()==fixedCapacity;
//    }
}
