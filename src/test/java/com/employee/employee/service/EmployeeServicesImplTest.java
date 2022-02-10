package com.employee.employee.service;

import com.employee.employee.data.dto.EmployeeDto;
import com.employee.employee.data.models.Employee;
import com.employee.employee.data.repository.EmployeeRepository;
import com.employee.employee.web.exception.EmployeeAlreadyExistsException;
import com.employee.employee.web.exception.EmployeeDoseNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@Slf4j
class EmployeeServicesImplTest {

    @Autowired
    EmployeeServices employeeServices;

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("get all the employees")
    void getEmployees() {
        List<Employee> employeeList = employeeServices.getAllEmployees();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(3);
    }

    @Test
    void findEmployeeById() throws EmployeeDoseNotExistException {
         Employee foundEmployee = employeeServices.findEmployeeById(12L);

         assertThat(foundEmployee).isNotNull();
         assertThat(foundEmployee.getUserName()).isEqualTo("luxury1");
    }

    @Test
    @DisplayName("create a new employee")
    void createEmployeeById() throws EmployeeAlreadyExistsException {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("jboy");
        employeeDto.setEmail("jboy@gmail.com");
        employeeDto.setPassword("1234");
        employeeDto.setMobileNumber("0803675289");
        employeeDto.setAddress("herbert marculy");
        employeeDto.setUserName("hmmmmmmm");

        assertThat(employeeDto).isNotNull();

        Employee newEmployee= employeeServices.createEmployee(employeeDto);

        assertThat(newEmployee.getId()).isNotNull();
        assertThat(newEmployee.getName()).isEqualTo("jboy");
        assertThat(newEmployee.getEmail()).isEqualTo("jboy@gmail.com");
        assertThat(newEmployee.getMobileNumber()).isEqualTo("0803675289");
        assertThat(newEmployee.getUserName()).isEqualTo("hmmmmmmm");

    }

    @Test
    void updateEmployeeDetails() {
    }

    @Test
    void deleteEmployeeById() {
    }
}