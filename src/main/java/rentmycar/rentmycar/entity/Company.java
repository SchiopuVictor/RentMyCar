package rentmycar.rentmycar.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company", schema = "rentmycar")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "tell")
    private String tell;

    @Column(name = "email")
    private String email;

    @Column(name = "cui")
    private String cui;

}