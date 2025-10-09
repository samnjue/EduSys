package com.api.edusys.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    
    private Long id;

    @NotBlank(message = "Subject name is required")
    private String name;

    @NotNull(message = "Teacher ID is required to assign a subject")
    private Long teacherId;

    private Set<Long> studentIds;
}
