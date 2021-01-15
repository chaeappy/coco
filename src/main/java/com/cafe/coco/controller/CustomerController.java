package com.cafe.coco.controller;

import com.cafe.coco.domain.Customer;
import com.cafe.coco.service.customerService;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    private final customerService customerService;
    String id = null;
    String password = null;

    @Autowired
    public CustomerController(customerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/new")
    public String joinCustomerForm() {
        return "customers/joinCustomerForm";
    }

    @PostMapping("/customers/new")
    public String create(CustomerForm customerForm) {
        Customer customer = new Customer();
        customer.setId(customerForm.getId());
        customer.setPassword(customerForm.getPassword());

        customer = customerService.join(customer);
        if (customer.getPk() != null)  {
            System.out.println("회원가입 완료");
            return "redirect:/";
        } else {
            System.out.println("회원가입 실패");
            return "redirect:/customers/new";
        }
    }

    @GetMapping("/customers/login")
    public String loginForm() {
        return "customers/loginCustomerForm";
    }

    @PostMapping("customers/login")
    public String login(CustomerForm customerForm) {
        id = customerForm.getId();
        password = customerForm.getPassword();
        Customer customer = customerService.findOne(id, password);
        System.out.println(customer);
        if (customer != null) {
            return "orders/orderForm";
        } else {
            System.out.println("로그인 실패");
            return "redirect:/customers/login";
        }
    }

    @GetMapping("/customers")
    public String list(Model model) {
        List<Customer> customers = customerService.findCustomers();
        model.addAttribute("customers", customers);
        return "customers/customerList";
    }



}
