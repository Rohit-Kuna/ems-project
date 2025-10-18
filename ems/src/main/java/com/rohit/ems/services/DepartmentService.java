package com.rohit.ems.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohit.ems.dtos.DepartmentResult;
import com.rohit.ems.entities.Department;
import com.rohit.ems.mappers.DepartmentMapper;
import com.rohit.ems.repositories.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<DepartmentResult> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(departmentMapper::toDto)
                .toList();
    }

    public DepartmentResult createDepartment(DepartmentResult request) {
        if (request == null || request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Department name is required");
        }

        Department department = new Department();
        department.setName(request.getName());
        departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    public DepartmentResult updateDepartment(Long id, DepartmentResult request) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        if (request.getName() != null && !request.getName().equals(existing.getName())) {
            existing.setName(request.getName());
        }

        departmentRepository.save(existing);
        return departmentMapper.toDto(existing);
    }

    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        departmentRepository.delete(department);
    }
}

