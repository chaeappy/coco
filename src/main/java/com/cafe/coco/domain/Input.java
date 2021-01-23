package com.cafe.coco.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Input {
    Long pk;
    String name;
    int price;
    int howMany;
    int total;

    public Input(Long pk, String name, int price, int howMany) {
        this.pk = pk;
        this.name = name;
        this.price = price;
        this.howMany = howMany;
        this.total = price * howMany;
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

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
        this.total = howMany * price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return name +
                ", " + howMany;
    }
}
