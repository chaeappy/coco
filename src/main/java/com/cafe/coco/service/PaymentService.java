package com.cafe.coco.service;

import com.cafe.coco.domain.Payment;
import com.cafe.coco.repository.PaymentRepository;

public class PaymentService {
    PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void save() {}
    public void printReceipt() {}
    public void cancelPayment(){}
}
