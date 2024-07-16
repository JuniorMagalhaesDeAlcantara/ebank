package com.ebank.ebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ebank.ebank.model.Customer;
import com.ebank.ebank.service.CustomerService;

@Controller
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Customer customer, Model model) {
        customerService.saveCustomer(customer);
        model.addAttribute("successMessage", "Conta criada com sucesso!");
        return "login";
    }
}
