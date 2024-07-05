package com.ebank.e_bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebank.e_bank.model.BankAccount;
import com.ebank.e_bank.service.BankAccountService;

@RestController
@RequestMapping("/bankAccounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<BankAccount> criarConta(@RequestBody BankAccount bankAccount) {
        BankAccount novaConta = bankAccountService.criarConta(bankAccount);
        return ResponseEntity.ok(novaConta);
    }

    @GetMapping
    public List<BankAccount> listarContas() {
        return bankAccountService.listarContas();
    }
}
