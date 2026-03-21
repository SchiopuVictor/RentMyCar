package rentmycar.rentmycar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentmycar.rentmycar.dtos.CustomerRequest;
import rentmycar.rentmycar.entity.Customer;
import rentmycar.rentmycar.exception.CustomerNotFoundException;
import rentmycar.rentmycar.mapper.CustomerMapper;
import rentmycar.rentmycar.repository.CustomerRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(CustomerRequest request) {
        Customer customer = CustomerMapper.toEntity(request);
        customer.setCreatedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer updateCustomer(CustomerRequest request, Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                CustomerNotFoundException::new);
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setDriverLicenseNumber(request.getDriverLicenseNumber());

        return customerRepository.save(customer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                CustomerNotFoundException::new);
        customerRepository.delete(customer);
    }

    public Customer findCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(
                CustomerNotFoundException::new);
    }


}
