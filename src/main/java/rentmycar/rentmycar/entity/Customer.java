package rentmycar.rentmycar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers", schema = "rentmycar")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private Set<Booking> bookings = new LinkedHashSet<>();

}