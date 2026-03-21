package rentmycar.rentmycar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars", schema = "rentmycar")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Long year;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "price_per_day")
    private BigDecimal pricePerDay;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany
    @JoinColumn(name = "car_id")
    private Set<Booking> bookings = new LinkedHashSet<>();

}