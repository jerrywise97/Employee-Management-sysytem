package com.employee.employee.data.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class EmployeeDto {

    private String name;
    private String mobileNumber;
    private String email;
    private String userName;
    private String password;
    private String address;


}
