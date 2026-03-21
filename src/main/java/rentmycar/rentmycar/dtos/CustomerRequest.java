package rentmycar.rentmycar.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private String driverLicenseNumber;


}
