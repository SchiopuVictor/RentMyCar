package rentmycar.rentmycar.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    private String carName;
    private String customerName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer totalDays;
    private BigDecimal pricePerDay;
    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime createdAt;

}
