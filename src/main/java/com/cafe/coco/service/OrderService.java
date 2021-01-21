package com.cafe.coco.service;

import com.cafe.coco.domain.Drink;
import com.cafe.coco.repository.OrderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 메뉴생성 및 출력
     */
    public List menu() {
        return orderRepository.menu();
    }

    /**
     * 메뉴선택
     */
    public HashMap input(Long pk) {
        return orderRepository.input(pk);

    }

    /**
     * 메뉴수정
     */
    public HashMap modifyInput() {
        return null;

    }

    /**
     * 토탈 금액확인
     */
    public Long total() {
        return null;
    }
}
