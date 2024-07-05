package com.ebank.e_bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebank.e_bank.model.Customer;
import com.ebank.e_bank.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer criarCliente(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> listarClientes() {
        return customerRepository.findAll();
    }

    // Outros métodos de serviço conforme necessário
}

