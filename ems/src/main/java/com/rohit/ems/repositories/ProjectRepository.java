package com.rohit.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rohit.ems.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>{
    
}
