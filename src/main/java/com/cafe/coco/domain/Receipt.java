package com.cafe.coco.domain;

import java.util.HashMap;

/**
 * :: Receipt class
 * 1. payment_pk - pk 넘버 ( payment foreign key)
 * 2. payment_way - 현금 또는 카드
 * 3. payment_cashReceipt - 현금영수증 번호
 * 4. send - HashMap
 * 5. payment_total
 */
public class Receipt {
    Long payment_pk;
    String payment_date;
    String payment_way;
    String payment_cashReceipt;
    HashMap<String, Object> send;
    int payment_total;

    public Receipt(Long payment_pk, String payment_date, String payment_way,
                   String payment_cashReceipt, HashMap<String, Object> send, int payment_total) {
        this.payment_pk = payment_pk;
        this.payment_date = payment_date;
        this.payment_way = payment_way;
        this.payment_cashReceipt = payment_cashReceipt;
        this.send = send;
        this.payment_total = payment_total;
    }
}
