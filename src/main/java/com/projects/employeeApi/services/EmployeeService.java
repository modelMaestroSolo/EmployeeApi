package com.projects.employeeApi.services;

import com.projects.employeeApi.domain.entities.Employee;
import org.postgresql.util.PSQLException;

import java.util.List;

public interface EmployeeService {
    List<Employee> fetchAllEmployees();

    Employee create(Employee employee);

    void update(Long id, Employee employee);

    Employee readOneEmployee(Long id);

    void deleteEmployeeById(Long id);
}
