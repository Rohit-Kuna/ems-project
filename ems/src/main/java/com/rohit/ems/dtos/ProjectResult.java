package com.rohit.ems.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectResult {
    @NotBlank
    private String name;
    private LocalDate projectStart;
    private LocalDate projectEnd;
}
