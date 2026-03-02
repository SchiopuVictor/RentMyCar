package rentmycar.rentmycar.exception;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException() {
        super("Invoice not found!");
    }
}
