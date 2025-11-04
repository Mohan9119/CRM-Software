package com.crm.backend.repository;

import com.crm.backend.model.Customer;
import com.crm.backend.model.Sale;
import com.crm.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findBySalesRep(User salesRep);
    List<Sale> findByCustomer(Customer customer);
    List<Sale> findByStatus(Sale.Status status);
}