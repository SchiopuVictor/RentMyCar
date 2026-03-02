package rentmycar.rentmycar.mapper;

import rentmycar.rentmycar.dtos.PaymentRequest;
import rentmycar.rentmycar.dtos.PaymentResponse;
import rentmycar.rentmycar.entity.Payment;

public class PaymentMapper {

    public static PaymentResponse toDto(Payment request) {
        return  PaymentResponse.builder()
                .paymentMethod(request.getPaymentMethod())
                .invoiceNumber(request.getInvoice().getInvoiceNumber())
                .paidAt(request.getPaidAt())
                .status(request.getStatus())
                .amount(request.getAmount())
                .build();
    }
}
