package com.ebank.e_bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebank.e_bank.model.BankAccount;
import com.ebank.e_bank.repository.BankAccountRepository;

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

