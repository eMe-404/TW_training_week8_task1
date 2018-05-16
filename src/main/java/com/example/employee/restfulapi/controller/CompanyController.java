package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private final
    CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping(value = "/companies")
    public List<Company> showAllCompany() {
        return companyRepository.findAll();
    }

    @GetMapping(value = "/companies/{id}")
    public Company showOneCompany(@PathVariable Long id) {
        return companyRepository.findOne(id);
    }

    @GetMapping(value = "/companies/{id}/employees")
    public List<Employee> showEmployeesInCompany(@PathVariable Long id) {
        Company company = companyRepository.findOne(id);
        return company.getEmployees();
    }

    @GetMapping(value = "/companies/page/{page}/pageSize/{pageSize}")
    public Page<Company> showEmployeesByPagination(@PathVariable(value = "page") int page
            , @PathVariable(value = "pageSize") int pageSize) {
        return companyRepository.findAll(new PageRequest(page-1, pageSize));
    }

    @PostMapping(value = "/companies")
    public Company createOneCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @PutMapping(value = "/companies/{id}")
    public Company updateOneCompany(@RequestBody Company company, @PathVariable Long id) {
        company.setId(id);
        return companyRepository.save(company);
    }

    @DeleteMapping(value = "/companies/{id}")
    public void deleteOneCompany(@PathVariable Long id) {
        companyRepository.delete(id);
    }


}
