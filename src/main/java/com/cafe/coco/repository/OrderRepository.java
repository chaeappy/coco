package com.cafe.coco.repository;

import com.cafe.coco.domain.Drink;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderRepository {
    // 메뉴출력
    List menu();

    // 메뉴선택
    HashMap input(Long pk);

    // 메뉴수정
    HashMap modifyInput();

    // 금액확인
    Long total();




}
