package main.java.com.api.edusys.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    
    private long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be of valid format")
    private String email;

    @Min(value = 16, message = "Student must be at least 16 years old")
    @Max(value = 60, message = "Student cannot be older than 60 years old")
    @NotuNull(message = "Age is required")
    private Integer age;
}
