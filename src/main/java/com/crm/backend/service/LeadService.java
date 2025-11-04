package com.crm.backend.service;

import com.crm.backend.dto.LeadDto;
import com.crm.backend.model.Lead;
import com.crm.backend.model.User;
import com.crm.backend.repository.LeadRepository;
import com.crm.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<LeadDto> getAllLeads() {
        return leadRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public LeadDto getLeadById(Long id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + id));
        return convertToDto(lead);
    }
    
    public LeadDto createLead(LeadDto leadDto) {
        Lead lead = convertToEntity(leadDto);
        Lead savedLead = leadRepository.save(lead);
        return convertToDto(savedLead);
    }
    
    public LeadDto updateLead(Long id, LeadDto leadDto) {
        Lead existingLead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + id));
        
        existingLead.setName(leadDto.getName());
        existingLead.setContactInfo(leadDto.getContactInfo());
        existingLead.setSource(leadDto.getSource());
        existingLead.setStatus(leadDto.getStatus());
        
        if (leadDto.getSalesRepId() != null) {
            User salesRep = userRepository.findById(leadDto.getSalesRepId())
                    .orElseThrow(() -> new RuntimeException("Sales rep not found with id: " + leadDto.getSalesRepId()));
            existingLead.setSalesRep(salesRep);
        }
        
        Lead updatedLead = leadRepository.save(existingLead);
        return convertToDto(updatedLead);
    }
    
    public void deleteLead(Long id) {
        if (!leadRepository.existsById(id)) {
            throw new RuntimeException("Lead not found with id: " + id);
        }
        leadRepository.deleteById(id);
    }
    
    private LeadDto convertToDto(Lead lead) {
        LeadDto dto = new LeadDto();
        dto.setId(lead.getId());
        dto.setName(lead.getName());
        dto.setContactInfo(lead.getContactInfo());
        dto.setSource(lead.getSource());
        dto.setStatus(lead.getStatus());
        
        if (lead.getSalesRep() != null) {
            dto.setSalesRepId(lead.getSalesRep().getId());
        }
        
        return dto;
    }
    
    private Lead convertToEntity(LeadDto dto) {
        Lead lead = new Lead();
        lead.setName(dto.getName());
        lead.setContactInfo(dto.getContactInfo());
        lead.setSource(dto.getSource());
        lead.setStatus(dto.getStatus());
        
        if (dto.getSalesRepId() != null) {
            User salesRep = userRepository.findById(dto.getSalesRepId())
                    .orElseThrow(() -> new RuntimeException("Sales rep not found with id: " + dto.getSalesRepId()));
            lead.setSalesRep(salesRep);
        }
        
        return lead;
    }
}