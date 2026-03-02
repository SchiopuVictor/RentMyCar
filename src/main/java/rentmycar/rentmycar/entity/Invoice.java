package rentmycar.rentmycar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table(name = "invoices", schema = "rentmycar")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "invoice_number")
    private Long invoiceNumber;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "issued_at")
    private LocalDate issuedAt;

    @OneToMany
    @JoinColumn(name = "invoice_id")
    private Set<Payment> payments = new LinkedHashSet<>();

    public void setBooking(Booking booking) {
        this.booking = booking;

        if (booking.getTotalPrice() != null) {
            this.amount = booking.getTotalPrice();
        }
    }
}