package com.crm.backend.dto;

import com.crm.backend.model.Lead;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LeadDto {
    private Long id;
    
    @NotBlank
    private String name;
    
    private String contactInfo;
    private Lead.Source source;
    private Lead.Status status;
    private Long salesRepId;
}