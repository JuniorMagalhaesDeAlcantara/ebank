package com.ebank.ebank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Transaction createTransaction(Transaction transaction) {
        BankAccount account = transaction.getAccount();
        
        if (account != null) {
            if (transaction.getAmount() < 0) {
                // Subtraia do saldo para saques ou despesas
                account.setBalance(account.getBalance() + transaction.getAmount());
            } else {
                // Adicione ao saldo para depósitos
                account.setBalance(account.getBalance() + transaction.getAmount());
            }
            bankAccountRepository.save(account);
        }
        
        return transactionRepository.save(transaction);
    }

    public Transaction deposit(Long accountId, double amount, String description) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    public Transaction withdraw(Long accountId, double amount, String description) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada"));

        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Saldo insuficiente");
        }

        account.setBalance(account.getBalance() - amount);
        bankAccountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(-amount); // Mantenha a quantidade negativa para saques
        transaction.setDescription(description);
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    public Transaction transfer(Long fromAccountId, Long toAccountId, double amount, String description) {
        BankAccount fromAccount = bankAccountRepository.findById(fromAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Conta de origem não encontrada"));
        BankAccount toAccount = bankAccountRepository.findById(toAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Conta de destino não encontrada"));

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Saldo insuficiente na conta de origem");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        bankAccountRepository.save(fromAccount);
        bankAccountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setAccount(fromAccount); // Associe a transação com a conta de origem
        transactionRepository.save(transaction);

        Transaction depositTransaction = new Transaction();
        depositTransaction.setAmount(amount);
        depositTransaction.setDescription(description);
        depositTransaction.setAccount(toAccount); // Associe a transação com a conta de destino
        return transactionRepository.save(depositTransaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByCustomerId(Long customerId) {
        return transactionRepository.findByAccountCustomerId(customerId);
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
