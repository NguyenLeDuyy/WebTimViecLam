package vn.hoidanit.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.dto.Meta;
import vn.hoidanit.jobhunter.domain.dto.ResultPaginationDTO;
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

    public ResultPaginationDTO fetchAllCompanies(Pageable pageable){
        Page<Company> pageCompany = this.companyRepository.findAll(pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        Meta mt = new Meta();

        mt.setPage(pageCompany.getNumber() + 1);
        mt.setPageSize(pageCompany.getSize());

        mt.setPages(pageCompany.getTotalPages());
        mt.setTotal(pageCompany.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pageCompany.getContent());

        return rs;
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
