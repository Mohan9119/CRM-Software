package com.crm.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lead {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;
    
    private String contactInfo;
    
    @Enumerated(EnumType.STRING)
    private Source source;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private User salesRep;
    
    public enum Source {
        REFERRAL, ADS, WEB
    }
    
    public enum Status {
        NEW, CONTACTED, CONVERTED, LOST
    }
}