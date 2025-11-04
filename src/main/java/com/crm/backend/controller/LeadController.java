package com.crm.backend.controller;

import com.crm.backend.dto.LeadDto;
import com.crm.backend.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @GetMapping
    public List<LeadDto> getAllLeads() {
        return leadService.getAllLeads();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadDto> getLeadById(@PathVariable Long id) {
        return ResponseEntity.ok(leadService.getLeadById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('SALES')")
    public ResponseEntity<LeadDto> createLead(@Valid @RequestBody LeadDto leadDto) {
        return ResponseEntity.ok(leadService.createLead(leadDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SALES')")
    public ResponseEntity<LeadDto> updateLead(@PathVariable Long id, @Valid @RequestBody LeadDto leadDto) {
        return ResponseEntity.ok(leadService.updateLead(id, leadDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
        return ResponseEntity.ok().build();
    }
}