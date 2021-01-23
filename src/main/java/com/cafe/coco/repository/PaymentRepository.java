package com.cafe.coco.repository;

public interface PaymentRepository {
    public void save();
    public void printReceipt();
    public void cancelPayment();

}
