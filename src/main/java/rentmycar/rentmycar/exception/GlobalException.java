package rentmycar.rentmycar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleException(MethodArgumentNotValidException ex){
        Map<String,String> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            map.put(error.getObjectName(),error.getDefaultMessage());});
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleException(InvoiceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Invoice not found!"));
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleException(BookingNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Booking not found!"));
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleException(CarNotFoundException ex){
        return ResponseEntity.badRequest().body(Map.of("message", "Car not found!"));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleException(CustomerNotFoundException ex){
        return ResponseEntity.badRequest().body(Map.of("message", "Customer not found!"));
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleException(PaymentNotFoundException ex){
        return ResponseEntity.badRequest().body(Map.of("message", "Payment not found!"));
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleException(CompanyNotFoundException ex){
        return ResponseEntity.badRequest().body(Map.of("message", "Company not found!"));
    }
}
