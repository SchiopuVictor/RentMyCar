package rentmycar.rentmycar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table(name = "payments", schema = "rentmycar")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "status")
    private String status;

    @Column(name = "paid_at", nullable = false)
    private LocalDate paidAt;

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
        if (invoice.getAmount() != null) {
            this.amount = invoice.getAmount();
        }
    }
}