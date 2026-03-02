package rentmycar.rentmycar.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException() {
        super("Car not found!");
    }
}
