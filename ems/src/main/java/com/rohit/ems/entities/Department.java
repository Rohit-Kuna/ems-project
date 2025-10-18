package com.rohit.ems.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="department")
public class Department extends BaseEntity {

    @Column(name="name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Employee> employees;

}
