package com.cafe.coco.domain;

public class Drink {
    Long pk;
    String name;
    int price;

    public Drink(Long pk, String name, int price) {
        this.pk = pk;
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return name +
                ", " + price;
    }
}
