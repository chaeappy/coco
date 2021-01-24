package com.cafe.coco.domain;

/**
 * :: Payment class
 * 1. pk - 매출넘버 ( order넘버와 동일 해야함 )
 * 2. data - 매출 생성시간
 * 3. id - 커스토머 아이디
 * 4. way - 현금 또는 카드
 * 5. total - 주문 총 금액 ( order 객체 total과 동일할 것 )
 * 6. cash_receipt - 고객 핸드폰번호 for ( 현금영수증 )
 * 7. receipt - 영수증 발행 여부
 */
public class Payment {
    Long order_pk;
    String date;
    String id;
    String way;
    int total;
    String cash_receipt;
    boolean receipt;

    public Payment(String date, String id, String way, int total, String cash_receipt, boolean receipt) {
        this.date = date;
        this.id = id;
        this.way = way;
        this.total = total;
        this.cash_receipt = cash_receipt;
        this.receipt = receipt;
    }

    public Long getOrder_pk() {
        return order_pk;
    }

    public void setOrder_pk(Long order_pk) {
        this.order_pk = order_pk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCash_receipt() {
        return cash_receipt;
    }

    public void setCash_receipt(String cash_receipt) {
        this.cash_receipt = cash_receipt;
    }

    public boolean isReceipt() {
        return receipt;
    }

    public void setReceipt(boolean receipt) {
        this.receipt = receipt;
    }
}
