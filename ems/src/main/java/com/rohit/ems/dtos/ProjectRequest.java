package com.rohit.ems.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectRequest {
    @NotBlank
    private String name;
    private LocalDate projectStart;
    private LocalDate projectEnd;

    public void setProjectEnd(LocalDate projectEnd){
        if(projectStart != null && projectEnd != null && !projectEnd.isAfter(this.projectStart)){
            throw new RuntimeException("the project end should be before project start");
        }
        this.projectEnd=projectEnd;
    }
}
