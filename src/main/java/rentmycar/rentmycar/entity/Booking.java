package rentmycar.rentmycar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings", schema = "rentmycar")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "total_days")
    private Integer totalDays;

    @Column(name = "price_per_day")
    private BigDecimal pricePerDay;

    @Setter(AccessLevel.NONE)
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany
    @JoinColumn(name = "booking_id")
    private Set<Invoice> invoices = new LinkedHashSet<>();

    public void calculateTotalPrice() {
        if (this.totalDays == null || this.pricePerDay == null) {
            return;
        }
        this.totalPrice = this.pricePerDay.multiply(new BigDecimal(this.totalDays));
    }


    private void calculateTotalDays() {
        if (startDate == null || endDate == null) return;
        long diff = ChronoUnit.DAYS.between(startDate, endDate);
        this.totalDays = (int) diff;
    }

    public void recalculate() {
        if (startDate == null || endDate == null || pricePerDay == null) return;
        calculateTotalDays();
        calculateTotalPrice();
    }


    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        recalculate();
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        recalculate();
    }


}