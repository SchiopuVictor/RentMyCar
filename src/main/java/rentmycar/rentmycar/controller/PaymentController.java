package rentmycar.rentmycar.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentmycar.rentmycar.dtos.PaymentRequest;
import rentmycar.rentmycar.dtos.PaymentResponse;
import rentmycar.rentmycar.mapper.PaymentMapper;
import rentmycar.rentmycar.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
private final PaymentService paymentService;

@PostMapping
public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest request) {
    return ResponseEntity.ok(PaymentMapper.toDto(paymentService.createPayment(request)));
}

@GetMapping("/{id}")
public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long id) {
    return ResponseEntity.ok(PaymentMapper.toDto(paymentService.getPayment(id)));
}

@PutMapping("/{id}")
public ResponseEntity<PaymentResponse> updatePayment(
        @PathVariable Long id,
        @Valid @RequestBody PaymentRequest request) {
    return ResponseEntity.ok(PaymentMapper.toDto(paymentService.updatePayment(request,id)));
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
    paymentService.deletePayment(id);
    return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}
