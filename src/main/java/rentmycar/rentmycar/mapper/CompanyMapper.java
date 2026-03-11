package rentmycar.rentmycar.mapper;

import rentmycar.rentmycar.dtos.CompanyRequest;
import rentmycar.rentmycar.dtos.CompanyResponse;
import rentmycar.rentmycar.entity.Company;

public class CompanyMapper {
public static CompanyResponse toDto(Company response){
   return CompanyResponse.builder()
            .email(response.getEmail())
            .phone(response.getTell())
            .address(response.getAddress())
            .companyName(response.getCompanyName())
            .build();
}

public static Company toEntity(CompanyRequest request){
    return Company.builder()
            .cui(request.getCui())
            .tell(request.getPhone())
            .companyName(request.getCompanyName())
            .email(request.getEmail())
            .address(request.getAddress())
            .build();
}

}
