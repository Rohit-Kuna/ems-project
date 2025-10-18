package com.rohit.ems.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.rohit.ems.dtos.GenderEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="employee")
public class Employee extends BaseEntity {

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="age")
    private Long age;

    @Column(name="email_id", nullable = false, unique = true, length = 255)
    private String emailId;

    @Column(name = "phone", nullable = false, unique = true)
    private Long phone;

    @Column(name="salary")
    private Long salary;

    @Column(name="joining_date")
    private LocalDate joiningDate;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    // employee is parent -> address is child
    // one employee can have multiple address
    // one address is associated to a single employee
    // fk column should be in child in onetomany/manytoone
    // hence, mappedBy is being mentioned here -> mappedBy = "fk POJO variable name mentioned in linked child"
    // where we mention mappedBy that entity doesn't have the fk
    // cascade -> always should be included at parent entity side, as deleting parent will remove all child enitity rows too
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address; // we mention this new initialization as it's safe for hibernate cascade operations and ensure null safety while dealing with DTOs

    // dept is parent, employee is child
    // Dept can have 0 or many employees
    // An employee must have 0 or single dept
    // we define the fk in child
    @ManyToOne(fetch=FetchType.EAGER) // Many the current entity to One the refrenced entity
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    private Department department;

    // many employee can have many projects
    // we can consider any one entity as owning side, here we take employee as owner, so project will be the inverse (mappedBy) side
    // we mention cascade details at the owning side
    // a new table is being formed with the joinColumn & inverseJoinColumn as composite pk
    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}) // this means this is the allowed operations on DBs
    @JoinTable(name="employee_projects", 
    joinColumns = @JoinColumn(name="employee_id", referencedColumnName = "id"),
    inverseJoinColumns=@JoinColumn(name="project_id", referencedColumnName="id") )
    private Set<Project> projects=new HashSet<>(); // we mention this new initialization as it's safe for hibernate cascade operations and ensure null safety while dealing with DTOs

}
