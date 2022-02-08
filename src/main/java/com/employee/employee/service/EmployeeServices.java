package com.employee.employee.service;

import com.employee.employee.data.models.Employee;
import com.employee.employee.data.dto.EmployeeDto;

import java.util.List;

public interface EmployeeServices {
    List<Employee> getEmployees();
    Employee findEmployeeById(Long employeeId);
    Employee createEmployeeById(EmployeeDto employeeDto);
    Employee updateEmployeeDetails();
}
