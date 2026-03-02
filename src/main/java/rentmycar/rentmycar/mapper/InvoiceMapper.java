package rentmycar.rentmycar.mapper;

import rentmycar.rentmycar.dtos.InvoiceResponse;
import rentmycar.rentmycar.entity.Invoice;

public class InvoiceMapper {

    public static InvoiceResponse toDto(Invoice response) {
        return InvoiceResponse.builder()
                .amount(response.getAmount())
                .invoiceNumber(response.getInvoiceNumber())
                .issuedAt(response.getIssuedAt())
                .bookingConfirmation(response.getBooking().getStatus())
                .build();
    }


}
