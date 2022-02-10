package com.employee.employee.service;

import com.employee.employee.data.models.Employee;
import com.employee.employee.data.dto.EmployeeDto;
import com.employee.employee.web.exception.BusinessLogicException;
import com.employee.employee.web.exception.EmployeeAlreadyExistsException;
import com.employee.employee.web.exception.EmployeeDoseNotExistException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

//import javax.json.JsonPatch;
import java.util.List;

public interface EmployeeServices {
    List<Employee> getAllEmployees();
    Employee findEmployeeById(Long employeeId) throws EmployeeDoseNotExistException;
    Employee createEmployee(EmployeeDto employeeDto) throws EmployeeAlreadyExistsException;
//    Employee updateEmployeeDetails(Long employeeId, JsonPatch employeePatch) throws BusinessLogicException, JsonPatchException, JsonProcessingException;
    void deleteEmployeeById(Long employeeId);
}
