package com.projects.employeeApi.controllers;

import com.projects.employeeApi.domain.dtos.EmployeeInputDto;
import com.projects.employeeApi.domain.entities.Employee;
import com.projects.employeeApi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/employee")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

  //  @PreAuthorize("hasRole('ADMIN')") //used to check a  condition before executing this method
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeInputDto employee) {
            return new ResponseEntity<>(employeeService.create(employee), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeInputDto employee) {
        employeeService.update(id, employee);
        return ResponseEntity.ok("Employee successfully updated!");
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") Long id){
        return employeeService.readOneEmployee(id);
    }

   // @PreAuthorize("hasRole('ADMIN')") method level authorization
    @GetMapping("/all-employees")
    public List<Employee> getAllEmployees() {
        return employeeService.fetchAllEmployees();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}
