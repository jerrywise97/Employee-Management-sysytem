package com.employee.employee.web.controller;


import com.employee.employee.data.dto.EmployeeDto;
import com.employee.employee.data.models.Employee;
import com.employee.employee.service.EmployeeServices;
import com.employee.employee.web.exception.EmployeeAlreadyExistsException;
import com.employee.employee.web.exception.EmployeeDoseNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    @Autowired
    EmployeeServices employeeServices;

    @GetMapping()
    public ResponseEntity<?> findAllEmployee(){
        List<Employee> employeeList = employeeServices.getAllEmployees();
        return ResponseEntity.ok().body(employeeList);
    }

    @PostMapping()
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto){
        try{
            Employee savedEmployee = employeeServices.createEmployee(employeeDto);
            return ResponseEntity.ok().body(savedEmployee);
        }catch(EmployeeAlreadyExistsException | IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable Long id){
        if(id == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("provide an employee id");

        try{
            Employee foundEmployee = employeeServices.findEmployeeById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(foundEmployee);
        }catch(EmployeeDoseNotExistException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}