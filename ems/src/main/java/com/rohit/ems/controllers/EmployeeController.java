package com.rohit.ems.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohit.ems.dtos.EmployeeRequest;
import com.rohit.ems.dtos.EmployeeResult;
import com.rohit.ems.services.EmployeeService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResult>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResult> getEmployeeById(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<EmployeeResult> createEmployee( @RequestBody EmployeeRequest employee) {
        return new ResponseEntity<>(employeeService.createEmployee(employee),HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResult> patchEmployeeById(@Validated @PathVariable Long id, @RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.patchEmployeeById(id,employeeRequest),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
