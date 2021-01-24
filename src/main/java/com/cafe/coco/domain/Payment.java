package com.cafe.coco.domain;

public class Payment {
    Long pk;
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
