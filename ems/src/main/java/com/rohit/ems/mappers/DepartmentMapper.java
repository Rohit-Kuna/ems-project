package com.rohit.ems.mappers;

import com.rohit.ems.dtos.DepartmentResult;
import com.rohit.ems.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentResult toDto(Department department);

    Department toEntity(DepartmentResult departmentDTO);
}
