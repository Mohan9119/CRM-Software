package com.crm.backend.dto;

import com.crm.backend.model.Task;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TaskDto {
    private Long id;
    
    @NotBlank
    private String title;
    
    private String description;
    private LocalDate dueDate;
    private Task.Priority priority;
    private Long assignedToId;
    private Task.Status status;
}