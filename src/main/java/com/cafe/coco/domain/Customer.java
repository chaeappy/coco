package com.cafe.coco.domain;

/**
 * 고객정보
 * 1. pk - increment 넘버, 프라이머리 키
 * 2. id - 고객 아이디, 유니크 키
 * 3. password - 비밀번호
 *
 */
public class Customer {
    Long pk;
    String id;
    String password;

    public Customer(Long pk, String id, String password) {
        this.pk = pk;
        this.id = id;
        this.password = password;
    }

    public Customer(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "pk=" + pk +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
