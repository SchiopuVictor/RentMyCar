package rentmycar.rentmycar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rentmycar.rentmycar.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
