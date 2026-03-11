package rentmycar.rentmycar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentmycar.rentmycar.dtos.CompanyRequest;
import rentmycar.rentmycar.entity.Company;
import rentmycar.rentmycar.mapper.CompanyMapper;
import rentmycar.rentmycar.repository.CompanyRespositroy;

@Service
@RequiredArgsConstructor
public class CompanyService {
private final CompanyRespositroy companyRespositroy;

@Transactional
public Company createCompany(CompanyRequest request){

   Company company = CompanyMapper.toEntity(request);
    return companyRespositroy.save(company);
}

@Transactional
public Company updateCompany(CompanyRequest request,long id){
    Company company = companyRespositroy.findById(id).orElseThrow(
            ()->new RuntimeException("company not found"));
    company.setCui(request.getCui());
    company.setCompanyName(request.getCompanyName());
    company.setTell(request.getPhone());
    company.setAddress(request.getAddress());
    company.setEmail(request.getEmail());
    return companyRespositroy.save(company);

}

@Transactional
public void deleteCompany(Long id){
    Company company = companyRespositroy.findById(id).orElseThrow(
            ()->new RuntimeException("Company not found"));
    companyRespositroy.delete(company);
}

public Company getCompany(Long id){
    return companyRespositroy.findById(id).orElseThrow(()->new RuntimeException("Company not found"));
}


}
