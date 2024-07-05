package com.ebank.e_bank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebank.e_bank.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    // Consultas personalizadas podem ser adicionadas aqui se necessário
}

