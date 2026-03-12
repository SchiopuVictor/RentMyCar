package rentmycar.rentmycar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentmycar.rentmycar.dtos.CompanyRequest;
import rentmycar.rentmycar.dtos.CompanyResponse;
import rentmycar.rentmycar.mapper.CompanyMapper;
import rentmycar.rentmycar.service.CompanyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompany(@PathVariable Long id) {
        return ResponseEntity.ok(CompanyMapper.toDto(companyService.getCompany(id)));
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest request) {
        return ResponseEntity.ok(CompanyMapper.toDto(companyService.createCompany(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@RequestBody CompanyRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(CompanyMapper.toDto(companyService.updateCompany(request,id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
