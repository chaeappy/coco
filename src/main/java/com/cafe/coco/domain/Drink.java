package com.cafe.coco.domain;

public class Drink {
    Long pk;
    String name;
    int price;
    String type;

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
        return name +
                ", " + price;
    }
}
