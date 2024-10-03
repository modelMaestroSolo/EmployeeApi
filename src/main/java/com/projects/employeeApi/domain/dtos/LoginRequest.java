package com.projects.employeeApi.domain.dtos;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
