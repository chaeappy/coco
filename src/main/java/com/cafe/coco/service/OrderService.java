package com.cafe.coco.service;

import com.cafe.coco.domain.Drink;
import com.cafe.coco.domain.Input;
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
    public ArrayList<Input> selectMenu(Drink drink) {
        return orderRepository.selectMenu(drink);

    }

    /**
     * 메뉴수정
     */
    public ArrayList<Input> modifyMenu(Input input, int howMany) {
        return orderRepository.modifyMenu(input, howMany);

    }

    /**
     * 토탈 금액확인
     */
    public Long total() {
        return null;
    }
}
