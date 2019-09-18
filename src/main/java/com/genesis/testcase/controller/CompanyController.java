package com.genesis.testcase.controller;

import com.genesis.testcase.beans.Address;
import com.genesis.testcase.beans.Company;
import com.genesis.testcase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    /**
     * Create company.
     *
     * @param company the company
     * @return the company
     */
    @PostMapping("/add")
    public Company creatCompany(@Valid @RequestBody Company company) {
        return companyRepository.save(company);
    }

    /**
     * Update company.
     *
     * @param companyId the company id
     * @param CompanyDetails the company details
     * @return the response entity
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity updateCompany(@PathVariable(value = "id") Long companyId,
                                                 @Valid @RequestBody Company CompanyDetails){
        try {
            Company company = companyRepository
                    .findById(companyId)
                    .orElseThrow(()
                            -> new EntityNotFoundException("Company not found on :: " + companyId));

            if(CompanyDetails.getName() != null)
                company.setName(CompanyDetails.getName());
            if(CompanyDetails.getAddresses() != null)
                company.setAddresses(CompanyDetails.getAddresses());
            if(CompanyDetails.getLegalinfo() != null)
                company.setLegalinfo(CompanyDetails.getLegalinfo());
            if(CompanyDetails.getContact() != null)
                company.setContact(CompanyDetails.getContact());

            final Company updatedCompany = companyRepository.save(company);
            return ResponseEntity.ok(updatedCompany);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.ok("error");
        }

    }

    /**
     * Update company main address.
     *
     * @param companyId the company id
     * @param addressId the company details
     * @return the response entity
     */
    @PutMapping("/editAddress/{id}")
    public ResponseEntity updateCompanyAddress(@PathVariable(value = "id") Long companyId,
                                                 @Valid @RequestBody Address addressId){
        try {
            Company company = companyRepository
                    .findById(companyId)
                    .orElseThrow(()
                            -> new EntityNotFoundException("Company not found on :: " + companyId));


            final Company updatedCompany = companyRepository.save(company);
            return ResponseEntity.ok(updatedCompany);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.ok("error");
        }

    }

}
