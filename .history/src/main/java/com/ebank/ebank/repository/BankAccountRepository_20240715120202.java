package com.ebank.ebank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebank.ebank.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    BankAccount findByCustomerId(Long customerId);
    // Consultas personalizadas podem ser adicionadas aqui se necessário
    
}

