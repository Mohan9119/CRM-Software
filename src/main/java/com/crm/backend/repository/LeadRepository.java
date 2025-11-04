package com.crm.backend.repository;

import com.crm.backend.model.Lead;
import com.crm.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    List<Lead> findBySalesRep(User salesRep);
    List<Lead> findByStatus(Lead.Status status);
}