package com.crm.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    
    @NotBlank
    private String name;
    
    @Email
    private String email;
    
    private String phone;
    private String company;
    private String address;
    private String notes;
    private Long salesRepId;
}