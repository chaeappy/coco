package com.cafe.coco.domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * :: Order class
 *
 */
public class Order {
    private Long pk;
    private ArrayList<Input> inputs;
    private int total;

    public Order(ArrayList<Input> inputs) {
        this.inputs = inputs;
        setTotal();
    }

    public Long getPk() {
        return pk;
    }

//    public void setPk(Long pk) {
//        this.pk = pk;
//    }

    public ArrayList<Input> getInputs() {
        return inputs;
    }

//    public void setInputs(ArrayList<Input> inputs) {
//        this.inputs = inputs;
//    }

    public int getTotal() {
        return total;
    }

    public void setTotal() {
        int sum = 0;
        for (int i = 0; i < this.inputs.size(); i++) {
            sum += this.inputs.get(i).getTotal();
        }
        this.total = sum;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < inputs.size(); i++) {
            str += inputs.get(i) + "\n";
        }
        str += "Total : " + total + "원";
        return str;
    }
}
