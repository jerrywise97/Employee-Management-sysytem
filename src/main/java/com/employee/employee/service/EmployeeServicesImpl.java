package com.employee.employee.service;

import com.employee.employee.data.dto.EmployeeDto;
import com.employee.employee.data.models.Employee;
import com.employee.employee.data.repository.EmployeeRepository;
import com.employee.employee.web.exception.BusinessLogicException;
import com.employee.employee.web.exception.EmployeeAlreadyExistsException;
import com.employee.employee.web.exception.EmployeeDoseNotExistException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServicesImpl implements EmployeeServices{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long employeeId) throws EmployeeDoseNotExistException {
        if(employeeId == null){
            throw new IllegalArgumentException("Id cannot be null.");
        }
        Optional<Employee> foundEmployee = employeeRepository.findById(employeeId);
        if(foundEmployee.isPresent()){
            return foundEmployee.get();
        }
        throw new EmployeeDoseNotExistException("Employee with ID :"+employeeId+": dose not exist");
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) throws EmployeeAlreadyExistsException {
        if(employeeDto == null){
            throw new IllegalArgumentException("Employee fields can not be empty.");
        }

        Optional<Employee> foundEmployee = employeeRepository.findByName(employeeDto.getName());
        if(foundEmployee.isPresent()){
            throw new EmployeeAlreadyExistsException("Employee with name" + employeeDto.getName() + "already exists");
        }
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setPassword(employeeDto.getPassword());
        employee.setEmail(employeeDto.getEmail());
        employee.setUserName(employeeDto.getUserName());
        employee.setMobileNumber(employeeDto.getMobileNumber());

        return employeeRepository.save(employee);
    }

    private Employee saveOrUpdate(Employee employee) throws BusinessLogicException {
        if (employee == null) {
            throw new BusinessLogicException("Product cannot be null");
        }
        return employeeRepository.save(employee);
    }

//    @Override
//    public Employee updateEmployeeDetails(Long employeeId, JsonPatch employeePatch) throws BusinessLogicException, JsonPatchException, JsonProcessingException {
//        Optional<Employee> employeeQuery = employeeRepository.findById(employeeId);
//        if(employeeQuery.isEmpty()){
//            throw new BusinessLogicException("Employee with ID" + employeeId + "Does not exist");
//        }
//
//        Employee targetEmployee = employeeQuery.get();
//
//        try{
//            targetEmployee = applyPatchToEmployee(employeePatch, targetEmployee){
//                log.info("employee after patch --> {}", targetEmployee);
//                return saveOrUpdate(targetEmployee);
//            }catch(JsonPatchException |JsonProcessingException | BusinessLogicException je){
//                throw new BusinessLogicException("update failed");                                                                )
//            }
//        }
//    }
    private Employee applyPatchToEmployee(JsonPatch employeepatch, Employee targetEmployee) throws JsonPatchException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = employeepatch.
                apply(objectMapper.convertValue(targetEmployee, JsonNode.class));

        return objectMapper.treeToValue(patched, Employee.class);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {

    }
}
