package com.crm.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;
    
    @Email
    private String email;
    
    private String phone;
    
    private String company;
    
    private String address;
    
    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private User salesRep;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
}