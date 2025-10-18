package com.rohit.ems.mappers;

import org.mapstruct.Mapper;

import com.rohit.ems.dtos.ProjectRequest;
import com.rohit.ems.dtos.ProjectResult;
import com.rohit.ems.entities.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResult entityToDto(Project project);

    Project resultToEntity(ProjectResult projectResult);

    Project requestToEntity(ProjectRequest request);

}

