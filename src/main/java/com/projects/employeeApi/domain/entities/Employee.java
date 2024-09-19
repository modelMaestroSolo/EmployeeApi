package com.projects.employeeApi.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    String password;
    Timestamp createdAt;
    Timestamp updatedAt;


}
