package com.ebank.ebank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebank.ebank.model.BankAccount;
import com.ebank.ebank.model.Transaction;
import com.ebank.ebank.repository.BankAccountRepository;
import com.ebank.ebank.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Transactional
    public Transaction deposit(Long customerId, double amount, String description) {
        BankAccount account = bankAccountRepository.findByCustomerId(customerId);

        if (account != null) {
            double oldBalance = account.getBalance();
            account.setBalance(oldBalance + amount);
            bankAccountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setType("Deposit");
            transaction.setDescription(description);
            transaction.setCustomer(account.getCustomer());
            transaction.setAccount(account); // Definir a conta associada à transação
            return transactionRepository.save(transaction);
        }
        return null; // Conta não encontrada
    }

    @Transactional
    public Transaction withdraw(Long customerId, double amount, String description) {
        BankAccount account = bankAccountRepository.findByCustomerId(customerId);

        if (account != null && account.getBalance() >= amount) {
            double oldBalance = account.getBalance();
            account.setBalance(oldBalance - amount);
            bankAccountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setAmount(-amount); // Mantenha a quantidade negativa para saques
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setType("Withdrawal");
            transaction.setDescription(description);
            transaction.setCustomer(account.getCustomer());
            transaction.setAccount(account); // Definir a conta associada à transação
            return transactionRepository.save(transaction);
        }
        return null; // Conta não encontrada ou saldo insuficiente
    }

    @Transactional
    public Transaction transfer(Long fromCustomerId, Long toCustomerId, double amount, String description) {
        BankAccount fromAccount = bankAccountRepository.findByCustomerId(fromCustomerId);
        BankAccount toAccount = bankAccountRepository.findByCustomerId(toCustomerId);

        if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
            double fromOldBalance = fromAccount.getBalance();
            double toOldBalance = toAccount.getBalance();

            fromAccount.setBalance(fromOldBalance - amount);
            toAccount.setBalance(toOldBalance + amount);

            bankAccountRepository.save(fromAccount);
            bankAccountRepository.save(toAccount);

            Transaction withdrawalTransaction = new Transaction();
            withdrawalTransaction.setAmount(-amount); // Mantenha a quantidade negativa para saques
            withdrawalTransaction.setTimestamp(LocalDateTime.now());
            withdrawalTransaction.setType("Transfer Out");
            withdrawalTransaction.setDescription(description);
            withdrawalTransaction.setCustomer(fromAccount.getCustomer());
            transactionRepository.save(withdrawalTransaction);

            Transaction depositTransaction = new Transaction();
            depositTransaction.setAmount(amount);
            depositTransaction.setTimestamp(LocalDateTime.now());
            depositTransaction.setType("Transfer In");
            depositTransaction.setDescription(description);
            depositTransaction.setCustomer(toAccount.getCustomer());
            
            return transactionRepository.save(depositTransaction);
        }
        return null; // Conta não encontrada ou saldo insuficiente
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByCustomerId(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        return optionalTransaction.orElse(null);
    }

    public Transaction updateTransaction(Long id, Transaction transaction) {
        if (transactionRepository.existsById(id)) {
            transaction.setId(id);
            return transactionRepository.save(transaction);
        }
        return null;
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
