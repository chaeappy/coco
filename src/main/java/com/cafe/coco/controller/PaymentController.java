package com.cafe.coco.controller;

import com.cafe.coco.domain.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    /**
     * 홈 화면
     */
    @GetMapping("/payments")
    public String home() {
        return "payments/paymentHome";
    }

    /**
     * 현금결제 화면
     */
    @GetMapping("/payments/cash")
    public String cash() {
        return "payments/cashForm";
    }

    /**
     * 결제하기
     */

}
