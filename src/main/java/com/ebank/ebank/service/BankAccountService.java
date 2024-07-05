package com.ebank.ebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebank.ebank.model.BankAccount;
import com.ebank.ebank.repository.BankAccountRepository;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount criarConta(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> listarContas() {
        return bankAccountRepository.findAll();
    }

    // Outros métodos de serviço conforme necessário
}

