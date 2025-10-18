package com.rohit.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rohit.ems.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    
}
