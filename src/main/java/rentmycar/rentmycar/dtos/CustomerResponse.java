package rentmycar.rentmycar.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String phone;
    private LocalDateTime createdAt;
    @JsonIgnore
    private String driverLicenseNumber;
}
