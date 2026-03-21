package rentmycar.rentmycar.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private String brand;
    private String model;
    private Long year;
    private String licensePlate;
    private BigDecimal pricePerDay;
    private String status;
    private LocalDateTime createdAt;
}
