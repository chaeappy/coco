package com.cafe.coco.controller;

import com.cafe.coco.domain.Customer;
import com.cafe.coco.domain.Order;
import com.cafe.coco.domain.Payment;
import com.cafe.coco.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
    public String cashForm(@SessionAttribute("customer") Customer customer,
                           @SessionAttribute("order") Order order,
                           @RequestParam Map<Object, Object> param) {
        String cash_receipt = (String) param.get("cash_receipt");
        String receipt = (String) param.get("receipt");
        if (param != null) {
            String date = nowDate();
            Payment payment = new Payment(date, customer, "현금", cash_receipt, order);
            paymentService.save(payment);
            return "0";
        } else {
            return "-1";
        }

    }

    /**
     * 결제취소
     */
    public void cancelPayment() {
        paymentService.cancelPayment();
    }




    /**
     * 영수증 재발행
     */
    @GetMapping("/payments/printReceipt")
    public String printReceipt() {
        return "payments/receiptForm";
    }

    @ResponseBody
    @RequestMapping("/payments/printReceipt")
    public Map<String, Object> suchPaymentInfo(@SessionAttribute("customer") Customer customer,
                                  @RequestParam Map<Object, Object> param) {
        String pk = (String) param.get("pk");
        Map<String, Object> payments = paymentService.printReceipt(customer, Long.parseLong(pk));
        return payments;
    }



    /**
     * 현재시각 메서드
     */
    public String nowDate() {
        SimpleDateFormat paymentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = paymentDate.format(new Date());
        return date;
    }
}
