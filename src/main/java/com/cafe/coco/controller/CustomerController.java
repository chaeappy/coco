package com.cafe.coco.controller;

import com.cafe.coco.domain.Customer;
import com.cafe.coco.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    private final customerService customerService;

    @Autowired
    public CustomerController(customerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/new")
    public String createForm() {
        return "customers/createCustomerForm";
    }

    @PostMapping("/customers/new")
    public String create(CustomerForm customerForm) {
        Customer customer = new Customer();
        customer.setId(customerForm.getId());
        customer.setPassword(customerForm.getPassword());

        customerService.join(customer);
        return "redirect:/";
    }

    @GetMapping("/customers/login")
    public String loginForm() {
        return "customers/loginCustomerForm";
    }

    @PostMapping("customers/login")
    public String login(CustomerForm customerForm) {
        Customer customer = new Customer();
        customer.setId(customerForm.getId());
        return "order";
    }

    @GetMapping("/customers")
    public String list(Model model) {
        List<Customer> customers = customerService.findCustomers();
        model.addAttribute("customers", customers);
        return "customers/customerList";
    }



}
