package com.cafe.coco.repository;

import com.cafe.coco.domain.Customer;
import com.cafe.coco.domain.Payment;

import java.util.Map;

public interface PaymentRepository {
    public void save(Payment payment);
    public Map<String, Object> printReceipt(Customer customer, Long pk);
    public void cancelPayment();
    public void logout();

}
