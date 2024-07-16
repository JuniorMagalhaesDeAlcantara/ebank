package com.ebank.ebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebank.ebank.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Consultas personalizadas podem ser adicionadas aqui se necessário
    Customer findByEmail(String email);
}

