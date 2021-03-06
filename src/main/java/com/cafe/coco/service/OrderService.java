package com.cafe.coco.service;

import com.cafe.coco.domain.Customer;
import com.cafe.coco.domain.Drink;
import com.cafe.coco.domain.Order;
import com.cafe.coco.repository.OrderRepository;

import java.util.HashMap;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 메뉴생성 및 출력
     * @param
     * @return
     */
    public HashMap<Long, Drink> menu() {
        return orderRepository.menu();
    }



    /**
     * 메뉴선택
     */
    public HashMap<String, Object> selectMenu(Long pk) {
        return orderRepository.selectMenu(pk);

    }

    /**
     * 메뉴수정
     */
    public HashMap<String, Object> modifyMenu(Long pk) {
        return orderRepository.modifyMenu(pk);

    }

    /**
     * 주문목록 생성
     */
    public Order createOrder(Customer customer) {
        return orderRepository.createOrder(customer);
    }

}
