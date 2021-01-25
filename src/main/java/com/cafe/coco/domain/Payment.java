package com.cafe.coco.domain;

/**
 * :: Payment class
 * 1. pk - 매출넘버 ( order넘버와 동일 해야함 )
 * 2. data - 매출 생성시간
 * 3. id - 커스토머 아이디
 * 4. cash - 현금 또는 카드
 * 5. total - 주문 총 금액 ( order 객체 total과 동일할 것 )
 * 6. cash_receipt - 고객 핸드폰번호 for ( 현금영수증 )
 */
public class Payment {
    Long pk;
    String date;
    Customer customer;
    String payment_way;
    Order order;
    String cash_receipt;

    public Payment(String date, Customer customer, String payment_way, Order order, String cash_receipt) {
        this.date = date;
        this.customer = customer;
        this.payment_way = payment_way;
        this.order = order;
        this.cash_receipt = cash_receipt;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPayment_way() {
        return payment_way;
    }

    public void setPayment_way(String payment_way) {
        this.payment_way = payment_way;
    }

    public int getTotal() {
        return this.order.getTotal();
    }

//    public void setTotal(int total) {
//        this.total = total;
//    }


    public String getCash_receipt() {
        return cash_receipt;
    }

    public void setCash_receipt(String cash_receipt) {
        this.cash_receipt = cash_receipt;
    }


}
