package com.projects.employeeApi.dao;

import com.projects.employeeApi.domain.entities.Employee;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public interface EmployeeDao {
    KeyHolder create(Employee employee, KeyHolder keyHolder);

    int update(Long id, Employee employee);

    List<Employee> retrieveAllEmployees();

    Employee fetchOneEmployee(Long id);

    int deleteEmployeeById(Long id);

    Boolean doesEmployeeExistByEmail(Employee employee);

}
