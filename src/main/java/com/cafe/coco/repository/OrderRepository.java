package com.cafe.coco.repository;

import com.cafe.coco.domain.Drink;

import java.util.HashMap;

public interface OrderRepository {
    /**
     * 메뉴생성 및 출력
     */
    HashMap menu();

    /**
     * 메뉴선택
     */
    HashMap input(Long pk);

    /**
     * @return
     */
    HashMap modifyInput();




}
