package com.projects.employeeApi.dao.Impl;

import com.projects.employeeApi.dao.EmployeeDao;
import com.projects.employeeApi.domain.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final JdbcTemplate template;
    public EmployeeDaoImpl(@Autowired JdbcTemplate template) {
        this.template = template;
    }

    public Integer existsByEmail(Employee employee){
        //check if email already exists
        String checkEmailSql = "select count(*) from employees where email = ?";
        return template.queryForObject(checkEmailSql, Integer.class, employee.getEmail());
    }

    public Integer existsById(Long id){
        //check if id already exists
        String checkEmailSql = "select count(*) from employees where id = ?";
        return template.queryForObject(checkEmailSql, Integer.class, id);
    }

    @Override
    public KeyHolder create(Employee employee, KeyHolder keyHolder) {

        // insert record with new email
        String sql = "insert into employees (name, email, password) values (?, ?, ?)";
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getPassword());
            return ps;
        }, keyHolder);
        return keyHolder;
    }

    @Override
    public int update(Long id, Employee employee) {
        String sql = "update employees set name = ?, email = ?, password = ? where id = ?";
        return template.update(sql, employee.getName(), employee.getEmail(), employee.getPassword(), id);
    }

    @Override
    public List<Employee> retrieveAllEmployees() {
        String sql = "select * from employees";
        RowMapper<Employee> rowMapper = new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                Employee employee = new Employee();
                employee.setId(rs.getLong("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                // employee.setPassword(rs.getString("password")); don't return password
                employee.setCreatedAt(rs.getTimestamp("created_at"));
                employee.setUpdatedAt(rs.getTimestamp("updated_at"));
                return employee;
            }
        };
        return template.query(sql, rowMapper);
    }

    @Override
    public Employee fetchOneEmployee(Long id) {
        String sql = "select * from employees where id = ?";
        RowMapper<Employee> rowMapper = new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                Employee employee = new Employee();
                employee.setId(rs.getLong("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
               // employee.setPassword(rs.getString("password")); don't return password
                employee.setCreatedAt(rs.getTimestamp("created_at"));
                employee.setUpdatedAt(rs.getTimestamp("updated_at"));
                return employee;
            }
        };
        return template.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int deleteEmployeeById(Long id) {
        String sql = "delete from employees where id = ?";
        return template.update(sql, id);
    }
}
