package com.ebank.e_bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebank.e_bank.model.Customer;
import com.ebank.e_bank.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> criarCliente(@RequestBody Customer customer) {
        Customer novoCliente = customerService.criarCliente(customer);
        return ResponseEntity.ok(novoCliente);
    }

    @GetMapping
    public List<Customer> listarClientes() {
        return customerService.listarClientes();
    }
}

