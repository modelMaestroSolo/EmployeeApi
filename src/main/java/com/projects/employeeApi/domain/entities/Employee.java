package com.projects.employeeApi.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Employee {

    Long id;
    String name;
    String email;
    String password;
    Timestamp createdAt;
    Timestamp updatedAt;


}
