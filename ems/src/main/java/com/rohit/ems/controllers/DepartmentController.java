package com.rohit.ems.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohit.ems.dtos.DepartmentResult;
import com.rohit.ems.services.DepartmentService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentResult>> getAllDepartments() {
        List<DepartmentResult> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PostMapping
    public ResponseEntity<DepartmentResult> createDepartment(@RequestBody DepartmentResult request) {
        DepartmentResult created = departmentService.createDepartment(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResult> updateDepartment(@PathVariable Long id,
                                                             @RequestBody DepartmentResult request) {
        DepartmentResult updated = departmentService.updateDepartment(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}

