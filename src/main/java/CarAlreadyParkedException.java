public class CarAlreadyParkedException extends Exception {
    public CarAlreadyParkedException() {
        super("This car is already parked");
    }
}
