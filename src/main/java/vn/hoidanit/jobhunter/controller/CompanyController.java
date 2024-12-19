package vn.hoidanit.jobhunter.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.service.CompanyService;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies")
    public ResponseEntity<Object> createCompany(@Valid @RequestBody Company postmanCompany){

        Company newCompany = this.companyService.handleCreateUser(postmanCompany);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCompany);
    }



}
