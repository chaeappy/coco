package com.cafe.coco.controller;

import com.cafe.coco.domain.Customer;
import com.cafe.coco.service.customerService;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
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

    @ResponseBody
    @PostMapping("/customers/new")
    public String create(@RequestBody String info) {
        String[] strArr = info.split(",");
        Customer customer = new Customer(strArr[0], strArr[1]);
        customer = customerService.join(customer);
        if (customer.getPk() != null)  {
            System.out.println("회원가입 완료");
            return "0";
        } else {
            System.out.println("[controller]회원가입 실패");
            return "-1";
        }
    }
//    @PostMapping("/customers/new")
//    public String create(CustomerForm customerForm) {
//        Customer customer = new Customer();
//        customer.setId(customerForm.getId());
//        customer.setPassword(customerForm.getPassword());
//        customer = customerService.join(customer);
//        if (customer.getPk() != null)  {
//            System.out.println("회원가입 완료");
//            return "home";
//        } else {
//            System.out.println("[controller]회원가입 실패");
//            return "customers/new";
//        }
//    }

    @ResponseBody
    @RequestMapping(value = "/customers/new/idCheck", method = RequestMethod.POST)
    // @RequestBody가 자바 객체로 변환해줌
    public String idCheck(@RequestBody String id) throws ParseException {
        if (customerService.findById(id)) {
            // 사용 가능한 id일 경우 0 리턴
            return "0";
        } else {
            // 사용중인 아이디 -1 리턴
            return "-1";
        }
    }


    @GetMapping("/customers/login")
    public String loginForm() {
        return "customers/loginCustomerForm";
    }

    @ResponseBody
    @PostMapping("/customers/login")
    public String login(@RequestBody String info) throws ParseException {
        String[] strArr = info.split(",");
        System.out.println("");
        System.out.println("");
        Customer customer = customerService.findOne(strArr[0], strArr[1]);
        if (customer != null) {
            return "0";
        } else {
            System.out.println("로그인 실패");
            return "-1";
        }
    }
//    @PostMapping("customers/login")
//    public String login(CustomerForm customerForm) {
//        id = customerForm.getId();
//        password = customerForm.getPassword();
//        Customer customer = customerService.findOne(id, password);
//        System.out.println(customer);
//        if (customer != null) {
//            return "orders/orderForm";
//        } else {
//            System.out.println("로그인 실패");
//            return "redirect:/customers/login";
//        }
//    }

    @GetMapping("/customers")
    public String list(Model model) {
        List<Customer> customers = customerService.findCustomers();
        model.addAttribute("customers", customers);
        return "customers/customerList";
    }



}
