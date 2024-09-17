package com.projects.employeeApi.services.Impl;

import com.projects.employeeApi.domain.entities.Employee;
import com.projects.employeeApi.dao.EmployeeDao;
import com.projects.employeeApi.exceptions.DatabaseError;
import com.projects.employeeApi.exceptions.EmailAlreadyExistsException;
import com.projects.employeeApi.exceptions.EmployeeNotFoundException;
import com.projects.employeeApi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(@Autowired EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee create(Employee employee) {
        Integer count = employeeDao.existsByEmail(employee);
        if(count != null && count > 0) {
            throw new EmailAlreadyExistsException("The Email provided is already associated with an employee!");
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        KeyHolder createdKeyHolder =  employeeDao.create(employee, keyHolder);

        // if the map of generated keys couldn't be returned eg: because insertion occurs within a transaction that fails or rolls back.
        Map<String, Object> keys = createdKeyHolder.getKeys();
        if (keys != null) {
            return readOneEmployee(Long.valueOf((Integer) keys.get("id")));
        }else {
            throw new DatabaseError("Database Error occurred while saving employee");
        }
    }

    @Override
    public void update(Long id, Employee employee) {
        int rowsAffected = employeeDao.update(id, employee);
        if(rowsAffected == 0){
            throw new EmployeeNotFoundException("Employee doesn't with id " + id + " doesn't exist");
        }
    }

    @Override
    public Employee readOneEmployee(Long id) {
        Integer count = employeeDao.existsById(id);
        if(count != null && count == 0) {
            throw new EmployeeNotFoundException("Invalid Employee Id!");
        }
        return employeeDao.fetchOneEmployee(id);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        int rowsAffected = employeeDao.deleteEmployeeById(id);
        if(rowsAffected == 0){
            throw new EmployeeNotFoundException("Employee with Id " + id + " not found");
        }
    }

    @Override
    public List<Employee> fetchAllEmployees() {
        return employeeDao.retrieveAllEmployees();
    }
}
