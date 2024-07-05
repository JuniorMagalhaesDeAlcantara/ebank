package com.ebank.ebank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebank.ebank.model.Customer;
import com.ebank.ebank.repository.CustomerRepository;

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

