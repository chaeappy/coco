package com.cafe.coco.repository;

import com.cafe.coco.domain.Payment;

public interface PaymentRepository {
    public void save(Payment payment);
    public void printReceipt();
    public void cancelPayment();
    public void logout();

}
