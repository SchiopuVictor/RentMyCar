package rentmycar.rentmycar.mapper;

import rentmycar.rentmycar.dtos.CustomerRequest;
import rentmycar.rentmycar.dtos.CustomerResponse;
import rentmycar.rentmycar.entity.Customer;

public class CustomerMapper {

    public static CustomerResponse toDto(Customer response){
        return CustomerResponse.builder()
                .firstName(response.getFirstName())
                .lastName(response.getLastName())
                .email(response.getEmail())
                .createdAt(response.getCreatedAt())
                .build();
    }

    public static Customer toEntity(CustomerRequest request){
            return Customer.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .driverLicenseNumber(request.getDriverLicenseNumber())
                    .createdAt(request.getCreatedAt())
                    .build();
    }
}
