package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    private final
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> showAllEmployees() {

        return employeeRepository.findAll();

    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public Employee showOneEmployee(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }

    @RequestMapping(value = "/employees/page/{page}/pageSize/{pageSize}", method = RequestMethod.GET)
    public Page<Employee> showEmployeesByPagination(@PathVariable(value = "page") int page
            , @PathVariable(value = "pageSize") int pageSize) {
        return employeeRepository.findAll(new PageRequest(page-1, pageSize));
    }


    @RequestMapping(value = "/employees/male", method = RequestMethod.GET)
    public List<Employee> showEmployeesByGender() {
        String male = "male";
        return employeeRepository.findByGender(male);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public Employee createOneEmployee(@RequestBody Employee employee) {

        return employeeRepository.save(employee);
    }


    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public Employee updateOneEmployee(@RequestBody Employee employee,@PathVariable Long id) {
        employee.setId(id);
        return employeeRepository.save(employee);
    }


    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public void deleteOneEmployee(@PathVariable Long id) {
        employeeRepository.delete(id);
    }
}
