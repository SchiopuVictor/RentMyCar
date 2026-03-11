package rentmycar.rentmycar.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rentmycar.rentmycar.entity.Invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PaymentResponse {

    private LocalDate paidAt;
    private String status;
    private String paymentMethod;
    private BigDecimal amount;
    private Long invoiceNumber;
    private String companyName;

}
