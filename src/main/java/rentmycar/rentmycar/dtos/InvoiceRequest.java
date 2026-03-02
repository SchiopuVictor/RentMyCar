package rentmycar.rentmycar.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceRequest {
    private Long bookingId;
    private Long invoiceNumber;
    private LocalDate issuedAt;
}
