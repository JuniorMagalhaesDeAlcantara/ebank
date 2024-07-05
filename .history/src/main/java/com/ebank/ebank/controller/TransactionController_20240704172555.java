package com.ebank.e_bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebank.e_bank.model.Transaction;
import com.ebank.e_bank.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> criarTransacao(@RequestBody Transaction transaction) {
        Transaction novaTransacao = transactionService.criarTransacao(transaction);
        return ResponseEntity.ok(novaTransacao);
    }

    @GetMapping
    public List<Transaction> listarTransacoes() {
        return transactionService.listarTransacoes();
    }
}

