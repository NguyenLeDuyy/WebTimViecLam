package vn.hoidanit.jobhunter.service;

import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company handleCreateUser(Company company) {
        return this.companyRepository.save(company);
    }

    public List<Company> fetchAllCompanies(){

        return this.companyRepository.findAll();
    }

    public Company fetchACompany(Long id){
        Optional<Company> optionalCompany = this.companyRepository.findById(id);

        if (optionalCompany.isPresent()){
            return optionalCompany.get();
        }

        return null;
    }

    public Company updateACompany(Company c){
        Company companyExist = fetchACompany(c.getId());
        if (companyExist != null){
            companyExist.setName(c.getName());
            companyExist.setDescription(c.getDescription());
            companyExist.setLogo(c.getLogo());
            companyExist.setAddress(c.getAddress());

            //update
            return this.companyRepository.save(companyExist);
        }
        return null;
    }

    public void handleDeleteACompany(Long id){
        this.companyRepository.deleteById(id);
    }
}
