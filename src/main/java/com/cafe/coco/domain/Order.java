package com.cafe.coco.domain;

import java.util.HashMap;

/**
 * :: Order class
 *
 */
public class Order {
    HashMap<Long, Input> order;

    public Order(HashMap<Long, Input> order) {
        this.order = order;
    }

    public HashMap<Long, Input> getOrder() {
        return order;
    }

    public void setOrder(HashMap<Long, Input> order) {
        this.order = order;
    }
}
