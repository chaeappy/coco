package com.cafe.coco.service;

import com.cafe.coco.domain.Drink;
import com.cafe.coco.repository.OrderRepository;

import java.util.HashMap;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 메뉴생성 및 출력
     */
    public HashMap menu() {
        return orderRepository.menu();
    }

    /**
     * 메뉴선택
     */
    public HashMap input() {

        return null;

    }

    /**
     * @return
     */
    public HashMap modifyInput() {
        return null;

    }
}
