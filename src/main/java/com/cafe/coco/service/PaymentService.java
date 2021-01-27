package com.cafe.coco.service;

import com.cafe.coco.domain.Customer;
import com.cafe.coco.domain.Payment;
import com.cafe.coco.domain.Receipt;
import com.cafe.coco.repository.OrderRepository;
import com.cafe.coco.repository.PaymentRepository;

import java.util.Map;

public class PaymentService {
    PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * 주문완료 후 디비저장
     */
    public void save(Payment payment) {
        paymentRepository.save(payment);

    }

    /**
     * 영수증 발행
     */
    public Map<String, Object> printReceipt(Customer customer, Long pk) {
        Map<String, Object> payments = paymentRepository.printReceipt(customer, pk);
        return payments;
    }


    /**
     * 결재취소
     */
    public String cancelPayment(Customer customer, Long pk){
        return paymentRepository.cancelPayment(customer, pk);
    }
}
