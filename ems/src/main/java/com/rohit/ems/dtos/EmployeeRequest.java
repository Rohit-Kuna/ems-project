package com.rohit.ems.dtos;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


// @Getter
// @Setter
@Data
public class EmployeeRequest {
    @NotBlank
    private String name;

    @Min(value = 20, message = "Employee age should be above 20")
    @Max(value = 60, message = "Employee age should be below 60")
    private Long age;

    @Email(message = "Enter a valid emailId") // or we can also use pattern
    // @Pattern(
    // regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
    // message = "Invalid email format"
    // )
    @NotBlank
    private String emailId;

    @Min(value = 1000000000, message = "Phone number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Phone number must be at most 10 digits")
    @NotBlank
    private Long phone;
    private Long salary;
    private LocalDate joiningDate;
    private GenderEnum gender;
    private AddressResult address;
    private String departmentName;
    private List<String> projectNames;
}
