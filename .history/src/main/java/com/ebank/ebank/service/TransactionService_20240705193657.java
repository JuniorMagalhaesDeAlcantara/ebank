package com.ebank.ebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebank.ebank.model.Transaction;
import com.ebank.ebank.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction criarTransacao(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> listarTransacoes() {
        return transactionRepository.findAll();
    }

    // Outros métodos de serviço conforme necessário
}

