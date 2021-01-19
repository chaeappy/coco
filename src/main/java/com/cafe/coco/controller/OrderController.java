package com.cafe.coco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/orders")
    public String orderHome() {
        return "orders/orderHome";
    }

    @GetMapping("/orders/orderForm")
    public String orderForm() {
        return "orders/orderForm";
    }


}
