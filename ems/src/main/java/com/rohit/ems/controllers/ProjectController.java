package com.rohit.ems.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohit.ems.dtos.ProjectRequest;
import com.rohit.ems.dtos.ProjectResult;
import com.rohit.ems.services.ProjectService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResult>> getAllProjects() {
        List<ProjectResult> projects = projectService.getAllProjects();
        // return ResponseEntity.ok(projects);
        return new ResponseEntity<>(projects,HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<ProjectResult> createProject(@RequestBody ProjectRequest request) {
        ProjectResult created = projectService.createProject(request);
        // return ResponseEntity.status(HttpStatus.CREATED).body(created);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResult> patchProject(@PathVariable Long id, @RequestBody ProjectRequest request) {
        ProjectResult updated = projectService.patchProject(id, request);
        // return ResponseEntity.ok(updated);
        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        // return ResponseEntity.noContent().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

