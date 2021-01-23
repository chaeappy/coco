package com.cafe.coco.repository;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

public class JdbcPaymentRepository implements PaymentRepository {
    private final DataSource dataSourceForPayment;

    public JdbcPaymentRepository(DataSource dataSourceForPayment) {
        this.dataSourceForPayment = dataSourceForPayment;
    }

    @Override
    public void save() {

    }

    @Override
    public void printReceipt() {

    }

    @Override
    public void cancelPayment() {

    }
}
