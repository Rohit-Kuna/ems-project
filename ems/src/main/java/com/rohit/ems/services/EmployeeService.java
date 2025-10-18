package com.rohit.ems.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.rohit.ems.repositories.AddressRepository;
import com.rohit.ems.repositories.DepartmentRepository;
import com.rohit.ems.repositories.EmployeeRepository;
import com.rohit.ems.repositories.ProjectRepository;

import lombok.extern.slf4j.Slf4j;

import com.rohit.ems.dtos.EmployeeRequest;
import com.rohit.ems.dtos.EmployeeResult;
import com.rohit.ems.entities.Address;
import com.rohit.ems.entities.Department;
import com.rohit.ems.entities.Employee;
import com.rohit.ems.entities.Project;
import com.rohit.ems.mappers.AddressMapper;
import com.rohit.ems.mappers.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    ProjectRepository projectRepository;

    public List<EmployeeResult> getAllEmployees() {
        return employeeRepository.findAll().stream().filter(Objects::nonNull).map(employeeMapper::entityToResult)
                .toList();
    }

    public EmployeeResult getEmployeeById(Long id) {
        return employeeMapper.entityToResult(employeeRepository.findById(id).orElse(new Employee()));
    }

    public EmployeeResult createEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest == null) {
            throw new RuntimeException("The request is empty");
        }
        Employee result = new Employee();
        result.setName(employeeRequest.getName());
        result.setAge(employeeRequest.getAge());
        result.setEmailId(employeeRequest.getEmailId());
        result.setPhone(employeeRequest.getPhone());
        result.setSalary(employeeRequest.getSalary());
        result.setJoiningDate(employeeRequest.getJoiningDate());
        result.setGender(employeeRequest.getGender());
        if (employeeRequest.getAddress() != null) {
            Address address = addressMapper.resultToEntity(employeeRequest.getAddress());
            address.setEmployee(result); // owning side reference setting foreignKey in address
            result.setAddress(address); // inverse side setting
        }
        if (employeeRequest.getDepartmentName()!=null && !employeeRequest.getDepartmentName().isBlank()) {
            Department department = departmentRepository.findByName(employeeRequest.getDepartmentName()).orElse(null);
            if (department == null) {
                department = new Department();
                department.setName(employeeRequest.getDepartmentName());
                departmentRepository.save(department);
            }
            result.setDepartment(department);
        }
        if (employeeRequest.getProjectNames()!=null && !employeeRequest.getProjectNames().isEmpty()) {
            List<String> requestedNames = employeeRequest.getProjectNames();
            Set<Project> projects = projectRepository.findAll().stream()
                    .filter(project -> requestedNames.contains(project.getName()))
                    .collect(Collectors.toSet());
            result.setProjects(projects);
        }
        employeeRepository.save(result);
        return employeeMapper.entityToResult(result);
    }

    public EmployeeResult patchEmployeeById(Long id, EmployeeRequest employeeRequest) {
        Employee existing = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("null"));

        if (!employeeRequest.getName().isBlank() && !employeeRequest.getName().equals(existing.getName())) {
            existing.setName(employeeRequest.getName());
        }

        if (employeeRequest.getAge() != null && !employeeRequest.getAge().equals(existing.getAge())) {
            existing.setAge(employeeRequest.getAge());
        }

        if (employeeRequest.getPhone() != null && !employeeRequest.getPhone().equals(existing.getPhone())) {
            existing.setPhone(employeeRequest.getPhone());
        }

        if (employeeRequest.getSalary() != null && !employeeRequest.getSalary().equals(existing.getSalary())) {
            existing.setSalary(employeeRequest.getSalary());
        }

        if (employeeRequest.getJoiningDate() != null
                && !employeeRequest.getJoiningDate().equals(existing.getJoiningDate())) {
            existing.setJoiningDate(employeeRequest.getJoiningDate());
        }

        if (employeeRequest.getGender() != null && !employeeRequest.getGender().equals(existing.getGender())) {
            existing.setGender(employeeRequest.getGender());
        }

        if (employeeRequest.getAddress() != null) {
            Address address = existing.getAddress();

            if (employeeRequest.getAddress().getHouseNumber() != null &&
                    !employeeRequest.getAddress().getHouseNumber().equals(address.getHouseNumber())) {
                address.setHouseNumber(employeeRequest.getAddress().getHouseNumber());
            }

            if (employeeRequest.getAddress().getStreet() != null &&
                    !employeeRequest.getAddress().getStreet().equals(address.getStreet())) {
                address.setStreet(employeeRequest.getAddress().getStreet());
            }

            if (employeeRequest.getAddress().getCity() != null &&
                    !employeeRequest.getAddress().getCity().equals(address.getCity())) {
                address.setCity(employeeRequest.getAddress().getCity());
            }

            if (employeeRequest.getAddress().getState() != null &&
                    !employeeRequest.getAddress().getState().equals(address.getState())) {
                address.setState(employeeRequest.getAddress().getState());
            }

            if (employeeRequest.getAddress().getZipcode() != null &&
                    !employeeRequest.getAddress().getZipcode().equals(address.getZipcode())) {
                address.setZipcode(employeeRequest.getAddress().getZipcode());
            }

        }

        if (employeeRequest.getDepartmentName()!=null && !employeeRequest.getDepartmentName().equals(existing.getDepartment().getName())){
            Department department=departmentRepository.findByName(employeeRequest.getDepartmentName()).orElse(null);
            if (department == null) {
                department = new Department();
                department.setName(employeeRequest.getDepartmentName());
                departmentRepository.save(department);
            }
            existing.setDepartment(department);
        }

        if (employeeRequest.getProjectNames()!=null && !employeeRequest.getProjectNames().isEmpty()) {
            List<String> requestedNames = employeeRequest.getProjectNames();
            Set<Project> projects = projectRepository.findAll().stream()
                    .filter(project -> requestedNames.contains(project.getName()))
                    .collect(Collectors.toSet());
            existing.setProjects(projects);
        }

        employeeRepository.save(existing);

        return employeeMapper.entityToResult(existing);

    }

    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }

}
