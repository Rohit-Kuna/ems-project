package com.rohit.ems.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="project")
public class Project extends BaseEntity {

    @Column(name="name", nullable = false, unique = true)
    private String name;
  
    @Column(name="project_start")
    private LocalDate projectStart;

    @Column(name="project_end")
    private LocalDate projectEnd;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees=new HashSet<>();

}
