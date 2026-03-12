package rentmycar.rentmycar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentmycar.rentmycar.dtos.PaymentRequest;
import rentmycar.rentmycar.entity.Company;
import rentmycar.rentmycar.entity.Invoice;
import rentmycar.rentmycar.entity.Payment;
import rentmycar.rentmycar.exception.CompanyNotFoundException;
import rentmycar.rentmycar.exception.InvoiceNotFoundException;
import rentmycar.rentmycar.exception.PaymentNotFoundException;
import rentmycar.rentmycar.repository.CompanyRespositroy;
import rentmycar.rentmycar.repository.InvoiceRepository;
import rentmycar.rentmycar.repository.PaymentRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentService {
private final PaymentRepository paymentRepository;
private final InvoiceRepository invoiceRepository;
private final PaymentPdfService paymentPdfService;
private final CompanyRespositroy companyRespositroy;

    @Transactional
    public Payment createPayment(PaymentRequest request){

        Invoice invoice = invoiceRepository.findById(request.getInvoiceId()).orElseThrow(
                InvoiceNotFoundException::new);
        Company company = companyRespositroy.findById(request.getCompanyId()).orElseThrow(
                CompanyNotFoundException::new);

        Payment payment =new Payment();
        payment.setInvoice(invoice);
        payment.setCompany(company);
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setStatus(request.getStatus());
        payment.setAmount(invoice.getAmount());
        payment.setPaidAt(LocalDate.now());
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment updatePayment(PaymentRequest request,Long id) {

        Payment payment = paymentRepository.findById(id).orElseThrow(
                PaymentNotFoundException::new);

        Invoice invoice = invoiceRepository.findById(request.getInvoiceId()).orElseThrow(
                InvoiceNotFoundException::new);

        Company company = companyRespositroy.findById(request.getCompanyId()).orElseThrow(
                CompanyNotFoundException::new);

        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setInvoice(invoice);
        payment.setCompany(company);
        payment.setStatus(request.getStatus());

        return paymentRepository.save(payment);
    }

    public Payment getPayment(Long id){
        return paymentRepository.findById(id).orElseThrow(
                PaymentNotFoundException::new);
    }

    @Transactional
    public void deletePayment(Long id){
        Payment payment = paymentRepository.findById(id).orElseThrow(
                PaymentNotFoundException::new);
        paymentRepository.delete(payment);
    }

    public byte[] getPaymentPdf(Long id){
        Payment payment = paymentRepository
                .findById(id)
                .orElseThrow(PaymentNotFoundException::new);
        return paymentPdfService.createPdf(payment);
    }
}
