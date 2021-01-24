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

    public void save(Payment payment) {
        paymentRepository.save(payment);
        if (payment.isReceipt()) {
//            (Long payment_pk, String payment_date, String getPayment_way,
//                    String cashReceipt, HashMap<String, Object> send, int payment_total)
            printReceipt();
        }
    }
    public void printReceipt() {}
    public void cancelPayment(){}
}
