package com.ebank.ebank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebank.ebank.model.BankAccount;
import com.ebank.ebank.repository.BankAccountRepository;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    // Método para listar todas as contas bancárias
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    // Método para obter uma conta bancária por ID
    public BankAccount getBankAccountById(Long id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    // Método para criar uma nova conta bancária
    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    // Método para atualizar uma conta bancária existente
    public BankAccount updateBankAccount(Long id, BankAccount bankAccountDetails) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(id);

        if (optionalBankAccount.isPresent()) {
            BankAccount existingBankAccount = optionalBankAccount.get();
            existingBankAccount.setAccountNumber(bankAccountDetails.getAccountNumber());
            existingBankAccount.setBalance(bankAccountDetails.getBalance());
            // Atualize outros campos conforme necessário
            return bankAccountRepository.save(existingBankAccount);
        }

        return null;
    }

    // Método para listar todas as contas bancárias com detalhes do cliente
    public List<BankAccount> getAllBankAccountsWithDetails() {
            return bankAccountRepository.findAllWithCustomerDetails();
        }

    // Método para deletar uma conta bancária por ID
    public void deleteBankAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }
}


