package com.cafe.coco.service;

import com.cafe.coco.domain.Payment;
import com.cafe.coco.domain.Receipt;
import com.cafe.coco.repository.OrderRepository;
import com.cafe.coco.repository.PaymentRepository;

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
    public void printReceipt() {}

    /**
     * 결재취소
     */
    public void cancelPayment(){}
}
