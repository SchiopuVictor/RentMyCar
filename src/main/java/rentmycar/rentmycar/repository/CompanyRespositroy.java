package rentmycar.rentmycar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rentmycar.rentmycar.entity.Company;

@Repository
public interface CompanyRespositroy extends CrudRepository<Company, Long> {
}
