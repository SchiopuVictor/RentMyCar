package rentmycar.rentmycar.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentmycar.rentmycar.dtos.CustomerRequest;
import rentmycar.rentmycar.dtos.CustomerResponse;
import rentmycar.rentmycar.mapper.CustomerMapper;
import rentmycar.rentmycar.service.CustomerService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request){
        return ResponseEntity.ok(CustomerMapper.toDto(customerService.createCustomer(request)));

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id){
        return ResponseEntity.ok(CustomerMapper.toDto(customerService.findCustomer(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @Valid @RequestBody CustomerRequest request,
            @PathVariable Long id)
    {
        return ResponseEntity.ok(CustomerMapper.toDto(customerService.updateCustomer(request, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
