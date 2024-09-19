package com.projects.employeeApi.services;

import com.projects.employeeApi.domain.dtos.EmployeeInputDto;
import com.projects.employeeApi.domain.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> fetchAllEmployees();

    Employee create(EmployeeInputDto employee);

    void update(Long id, EmployeeInputDto employee);

    Employee readOneEmployee(Long id);

    void deleteEmployeeById(Long id);
}
