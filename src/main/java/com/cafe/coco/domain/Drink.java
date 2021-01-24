package com.cafe.coco.domain;
/**
 * 드링크
 * 1. pk - 프라이머리 키, increment
 * 2. name - 음료명, 유니크 키
 * 3. price - 가격
 * 4. type - 종류 (커피, 티, 에이드 등) for (주문페이지 메뉴목록을 타입별로 페이지 분류할 예정)
 *
 * :: 시스템 동작시 db에서 음료정보 받아와 드링크 객체화하기. 이후 ArrayList_menu 에 담는다.
 */
public class Drink {
    private Long pk;
    private String name;
    private int price;
    private String type;

    public Drink(Long pk, String name, int price, String type) {
        this.pk = pk;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getKind() {
        return type;
    }

    public void setKind(String kind) {
        this.type = kind;
    }

    @Override
    public String toString() {
        return name;
    }
}
