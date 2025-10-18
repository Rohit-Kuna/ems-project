package com.rohit.ems.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohit.ems.dtos.ProjectRequest;
import com.rohit.ems.dtos.ProjectResult;
import com.rohit.ems.entities.Project;
import com.rohit.ems.mappers.ProjectMapper;
import com.rohit.ems.repositories.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    public List<ProjectResult> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::entityToDto)
                .toList();
    }

    public ProjectResult createProject(ProjectRequest request) {
        if (request == null || request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Project name is required");
        }

        Project project = projectMapper.requestToEntity(request);
        projectRepository.save(project);
        return projectMapper.entityToDto(project);
    }

    public ProjectResult patchProject(Long id, ProjectRequest request) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (request.getName() != null && !request.getName().equals(existing.getName())) {
            existing.setName(request.getName());
        }

        if (request.getProjectStart() != null && !request.getProjectStart().equals(existing.getProjectStart())) {
            existing.setProjectStart(request.getProjectStart());
        }

        if (request.getProjectEnd() != null && !request.getProjectEnd().equals(existing.getProjectEnd())) {
            if (existing.getProjectStart() != null && !request.getProjectEnd().isAfter(existing.getProjectStart())) {
                throw new RuntimeException("Project end date must be after start date");
            }
            existing.setProjectEnd(request.getProjectEnd());
        }

        projectRepository.save(existing);
        return projectMapper.entityToDto(existing);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectRepository.delete(project);
    }
}


