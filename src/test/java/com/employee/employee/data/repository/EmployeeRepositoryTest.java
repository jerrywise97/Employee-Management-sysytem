package com.employee.employee.data.repository;

import com.employee.employee.data.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("save a new employee to the datebase")
    public void saveEmployeeInTheDataBaseTest(){
        //given i create a new employee
        Employee employee = new Employee();
        employee.setName("jerry");
        employee.setEmail("@gmail.com");
        employee.setMobileNumber("08032912334");
        employee.setPassword("jerry289");
        employee.setUserName("simplejey");

        assertThat(employee.getId()).isNull();
        //when i save employee
        employeeRepository.save(employee);

        //then i check if the employee is save
        log.info("employee saved :: {}", employee);

        assertThat(employee.getId()).isNotNull();
        assertThat(employee.getName()).isEqualTo("jerry");
        assertThat(employee.getEmail()).isEqualTo("@gmail.com");
        assertThat(employee.getMobileNumber()).isEqualTo("08032912334");
        assertThat(employee.getUserName()).isEqualTo("simplejey");
    }

    @Test
    @DisplayName("find an existing employee from the database")
    public void findExistingEmployeeFromDatabaseTest(){
        Employee employee = employeeRepository.findById(12L).orElse(null);

        assert employee != null;
        assertThat(employee.getId()).isEqualTo(12);
        assertThat(employee.getName()).isEqualTo("giidy");
        assertThat(employee.getEmail()).isEqualTo("chris1@gmail.com");
        assertThat(employee.getMobileNumber()).isEqualTo("08037227");
        assertThat(employee.getUserName()).isEqualTo("luxury1");

        log.info("employee saved :: {}", employee);
    }

    @Test
    @DisplayName("find all employee in the database")
    public void findAllEmployeeTest(){
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList.size()).isEqualTo(4);
        assertThat(employeeList).isNotNull();
    }

    @Test
    @DisplayName("delete an employee from the database")
    public void deleteAnEmployeeFromTheDataBaseTest(){
        employeeRepository.deleteById(13L);

        Employee retrieveEmployee = employeeRepository.findById(13L).orElse(null);
        assertThat(retrieveEmployee).isNull();
        assertThat(employeeRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("")
    public void findEmployeeInDataBaseByNameTest(){
       Employee foundEmployee= employeeRepository.findByName("giidy").orElse(null);

       assertThat(foundEmployee).isNotNull();
       assertThat(foundEmployee.getId()).isEqualTo(12);
       assertThat(foundEmployee.getName()).isEqualTo("giidy");
       assertThat(foundEmployee.getMobileNumber()).isEqualTo("08037227");

    }
}