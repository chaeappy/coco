package com.cafe.coco.repository;

import com.cafe.coco.domain.Customer;
import com.cafe.coco.domain.Drink;
import com.cafe.coco.domain.Input;
import com.cafe.coco.domain.Order;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderRepository {
    // 메뉴출력
    HashMap<Long, Drink> menu();

    // 메뉴선택
    HashMap<String, Object> selectMenu(Long pk);

    // 메뉴수정
    HashMap<String, Object> modifyMenu(Long pk);

    // 금액확인
    int total();

    // 주문목록 생성
    Order createOrder(Customer customer);




}
