package vn.hoidanit.jobhunter.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.service.CompanyService;

import java.util.List;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company postmanCompany){

        Company newCompany = this.companyService.handleCreateUser(postmanCompany);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCompany);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> companies = this.companyService.fetchAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @PutMapping("/companies")
    public ResponseEntity<Company> updateACompany(@Valid @RequestBody Company companyUpdate){

        Company updateCompany = this.companyService.updateACompany(companyUpdate);

        return ResponseEntity.ok(updateCompany);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteACompany(@PathVariable("id") Long id){

        this.companyService.handleDeleteACompany(id);

        return ResponseEntity.ok(null);
    }

}
