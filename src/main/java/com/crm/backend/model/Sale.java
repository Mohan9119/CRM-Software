package com.crm.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    
    private BigDecimal amount;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private User salesRep;
    
    public enum Status {
        PROPOSAL, NEGOTIATION, CLOSED_WON, CLOSED_LOST
    }
}