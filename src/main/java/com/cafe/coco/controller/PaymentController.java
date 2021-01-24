package com.cafe.coco.controller;

import com.cafe.coco.domain.Customer;
import com.cafe.coco.domain.Order;
import com.cafe.coco.domain.Payment;
import com.cafe.coco.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PaymentController {
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

//    /**
//     * 결제 홈 화면
//     */
//    @GetMapping("/payments")
//    public String home() {
//        return "payments/paymentHome";
//    }

    /**
     * 현금결제 화면
     */
    @GetMapping("/payments/cash")
    public String cash() {
        return "payments/cashForm";
    }

    /**
     * 현금결제 기능
     */
    @ResponseBody
    @RequestMapping("/payments/cash")
    public String cashForm(HttpSession httpSession, String cash_receipt, boolean receipt) {
        String way = "현금";
        int total = 0;
        Customer customer = (Customer) httpSession.getAttribute("customer");
        Order order = (Order) httpSession.getAttribute("order");
        SimpleDateFormat paymentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = paymentDate.format(new Date());
//        String date, String id, String way, int total
        Payment payment = new Payment(date, customer, way, order, cash_receipt, receipt);
        paymentService.save(payment);
        return "";

    }

    /**
     * 영수증 재발행
     */

    /**
     * 결제취소
     */

}
