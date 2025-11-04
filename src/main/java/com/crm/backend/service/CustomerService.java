package com.crm.backend.service;

import com.crm.backend.dto.CustomerDto;
import com.crm.backend.model.Customer;
import com.crm.backend.model.User;
import com.crm.backend.repository.CustomerRepository;
import com.crm.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return convertToDto(customer);
    }
    
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = convertToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDto(savedCustomer);
    }
    
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        
        existingCustomer.setName(customerDto.getName());
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setPhone(customerDto.getPhone());
        existingCustomer.setCompany(customerDto.getCompany());
        existingCustomer.setAddress(customerDto.getAddress());
        existingCustomer.setNotes(customerDto.getNotes());
        
        if (customerDto.getSalesRepId() != null) {
            User salesRep = userRepository.findById(customerDto.getSalesRepId())
                    .orElseThrow(() -> new RuntimeException("Sales rep not found with id: " + customerDto.getSalesRepId()));
            existingCustomer.setSalesRep(salesRep);
        }
        
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return convertToDto(updatedCustomer);
    }
    
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
    
    private CustomerDto convertToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setCompany(customer.getCompany());
        dto.setAddress(customer.getAddress());
        dto.setNotes(customer.getNotes());
        
        if (customer.getSalesRep() != null) {
            dto.setSalesRepId(customer.getSalesRep().getId());
        }
        
        return dto;
    }
    
    private Customer convertToEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setCompany(dto.getCompany());
        customer.setAddress(dto.getAddress());
        customer.setNotes(dto.getNotes());
        
        if (dto.getSalesRepId() != null) {
            User salesRep = userRepository.findById(dto.getSalesRepId())
                    .orElseThrow(() -> new RuntimeException("Sales rep not found with id: " + dto.getSalesRepId()));
            customer.setSalesRep(salesRep);
        }
        
        return customer;
    }
}