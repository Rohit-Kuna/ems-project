package com.rohit.ems.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// @Getter
// @Setter
@Data
public class EmployeeResult {
    private String id;
    private String name;
    private Long age;
    private String emailId;
    private Long phone;
    private Long salary;
    private LocalDate joiningDate;
    private GenderEnum gender;
    private AddressResult address;
    private DepartmentResult department;
    private List<ProjectResult> projects;
}
