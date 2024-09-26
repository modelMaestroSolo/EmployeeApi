package com.projects.employeeApi.domain.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class EmployeeInputDto {
        String name;
        String email;
        String password;
}
