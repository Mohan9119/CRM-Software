package com.crm.backend.service;

import com.crm.backend.dto.SaleDto;
import com.crm.backend.model.Customer;
import com.crm.backend.model.Sale;
import com.crm.backend.model.User;
import com.crm.backend.repository.CustomerRepository;
import com.crm.backend.repository.SaleRepository;
import com.crm.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<SaleDto> getAllSales() {
        return saleRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public SaleDto getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + id));
        return convertToDto(sale);
    }
    
    public SaleDto createSale(SaleDto saleDto) {
        Sale sale = convertToEntity(saleDto);
        Sale savedSale = saleRepository.save(sale);
        return convertToDto(savedSale);
    }
    
    public SaleDto updateSale(Long id, SaleDto saleDto) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + id));
        
        existingSale.setAmount(saleDto.getAmount());
        existingSale.setDate(saleDto.getDate());
        existingSale.setStatus(saleDto.getStatus());
        
        if (saleDto.getCustomerId() != null) {
            Customer customer = customerRepository.findById(saleDto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + saleDto.getCustomerId()));
            existingSale.setCustomer(customer);
        }
        
        if (saleDto.getSalesRepId() != null) {
            User salesRep = userRepository.findById(saleDto.getSalesRepId())
                    .orElseThrow(() -> new RuntimeException("Sales rep not found with id: " + saleDto.getSalesRepId()));
            existingSale.setSalesRep(salesRep);
        }
        
        Sale updatedSale = saleRepository.save(existingSale);
        return convertToDto(updatedSale);
    }
    
    public void deleteSale(Long id) {
        if (!saleRepository.existsById(id)) {
            throw new RuntimeException("Sale not found with id: " + id);
        }
        saleRepository.deleteById(id);
    }
    
    private SaleDto convertToDto(Sale sale) {
        SaleDto dto = new SaleDto();
        dto.setId(sale.getId());
        dto.setAmount(sale.getAmount());
        dto.setDate(sale.getDate());
        dto.setStatus(sale.getStatus());
        
        if (sale.getCustomer() != null) {
            dto.setCustomerId(sale.getCustomer().getId());
        }
        
        if (sale.getSalesRep() != null) {
            dto.setSalesRepId(sale.getSalesRep().getId());
        }
        
        return dto;
    }
    
    private Sale convertToEntity(SaleDto dto) {
        Sale sale = new Sale();
        sale.setAmount(dto.getAmount());
        sale.setDate(dto.getDate());
        sale.setStatus(dto.getStatus());
        
        if (dto.getCustomerId() != null) {
            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + dto.getCustomerId()));
            sale.setCustomer(customer);
        }
        
        if (dto.getSalesRepId() != null) {
            User salesRep = userRepository.findById(dto.getSalesRepId())
                    .orElseThrow(() -> new RuntimeException("Sales rep not found with id: " + dto.getSalesRepId()));
            sale.setSalesRep(salesRep);
        }
        
        return sale;
    }
}