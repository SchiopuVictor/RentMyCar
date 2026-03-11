package rentmycar.rentmycar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentmycar.rentmycar.dtos.InvoiceRequest;
import rentmycar.rentmycar.entity.Booking;
import rentmycar.rentmycar.entity.Invoice;
import rentmycar.rentmycar.exception.BookingNotFoundException;
import rentmycar.rentmycar.exception.InvoiceNotFoundException;
import rentmycar.rentmycar.repository.BookingRepository;
import rentmycar.rentmycar.repository.InvoiceRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final BookingRepository bookingRepository;

    @Transactional
    public Invoice createInvoice(InvoiceRequest request){
       Booking booking = bookingRepository.findById(request.getBookingId()).orElseThrow(
               BookingNotFoundException::new);

       Invoice invoice = new Invoice();
       invoice.setBooking(booking);
       invoice.setAmount(booking.getTotalPrice());
       invoice.setInvoiceNumber(request.getInvoiceNumber());
       invoice.setIssuedAt(LocalDate.now());
       return invoiceRepository.save(invoice);
    }

    @Transactional
    public Invoice updateInvoice(InvoiceRequest request, Long id){
       Invoice invoice = invoiceRepository.findById(id).orElseThrow(
               InvoiceNotFoundException::new);

       Booking booking = bookingRepository.findById(request.getBookingId()).orElseThrow(
               BookingNotFoundException::new);

       invoice.setBooking(booking);
       invoice.setInvoiceNumber(request.getInvoiceNumber());
       invoice.setIssuedAt(request.getIssuedAt());
       return invoiceRepository.save(invoice);
    }

    public Invoice getInvoice(Long id){
        return invoiceRepository.findById(id).orElseThrow(InvoiceNotFoundException::new);
    }

    @Transactional
    public void deleteInvoice(Long id){
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(
                InvoiceNotFoundException::new);
        invoiceRepository.delete(invoice);

    }
}
