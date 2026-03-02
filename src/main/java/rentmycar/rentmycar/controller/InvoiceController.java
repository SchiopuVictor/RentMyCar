package rentmycar.rentmycar.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentmycar.rentmycar.dtos.InvoiceRequest;
import rentmycar.rentmycar.dtos.InvoiceResponse;
import rentmycar.rentmycar.mapper.InvoiceMapper;
import rentmycar.rentmycar.service.InvoiceService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<InvoiceResponse> createInvoice(
            @Valid @RequestBody InvoiceRequest request) {
        return ResponseEntity.ok(InvoiceMapper.toDto(invoiceService.createInvoice(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponse> updateInvoice(
            @Valid @RequestBody InvoiceRequest request,
            @PathVariable Long id)
    {
        return ResponseEntity.ok(InvoiceMapper.toDto(invoiceService.updateInvoice(request, id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> getInvoice(
            @PathVariable Long id)
    {
        return ResponseEntity.ok(InvoiceMapper.toDto(invoiceService.getInvoice(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(Long id)
    {
        invoiceService.deleteInvoice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
