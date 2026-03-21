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
public class BookingRequest {
    private Long carId;
    private Long customerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private LocalDateTime createdAt;

}
