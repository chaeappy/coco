package com.cafe.coco.controller;

import com.cafe.coco.domain.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    @GetMapping("/payments")
    public String home() {
        return "payments/paymentHome";
    }

}
