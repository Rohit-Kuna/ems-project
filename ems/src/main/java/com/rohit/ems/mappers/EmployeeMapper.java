package com.rohit.ems.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.rohit.ems.dtos.EmployeeResult;
import com.rohit.ems.entities.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee resultToEntity(EmployeeResult employeeResult);

    EmployeeResult entityToResult(Employee employee);
}
