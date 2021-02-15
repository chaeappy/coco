package com.cafe.coco.controller;

import com.cafe.coco.domain.Customer;
import com.cafe.coco.domain.Drink;
import com.cafe.coco.domain.Order;
import com.cafe.coco.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SessionAttributes("order")
@Controller
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String orderHome() {
        return "orders/orderHome";
    }

    @GetMapping("/orders/orderForm")
    public String orderForm(HttpSession httpSession, Model model) {
        HashMap<Long, Drink> drinks = orderService.menu();
        ArrayList<Drink> menus = new ArrayList<Drink>(drinks.values());
        model.addAttribute("menus", menus);
        if (!model.containsAttribute("map")) {
            model.addAttribute("map", new HashMap<String, Object>());
            System.out.println("ok");
        }
        return "orders/orderForm";
    }


    /**
     * 메뉴선택
     */

//    @ResponseBody
//    @RequestMapping("/customers/orderForm")
//    public String selectMenu(@RequestBody ArrayList<Input> inputs, Model model) {
//        model.addAttribute("inputs", inputs);
//        return"orders/orderForm";
//    }

    @ResponseBody
    @RequestMapping("/orders/orderForm")
    public String selectMenu(@RequestBody String str,
                             @ModelAttribute("map") HashMap<String, Object> map) {
        Long pk = Long.valueOf(str);
        orderService.selectMenu(pk);
        HashMap<String, Object> send = orderService.selectMenu(pk);
        map.put("inputs", send.get("inputs"));
        map.put("total", send.get("total"));
        return "0";
    }

    @GetMapping("/orders/orderList")
    public void orderList() {

    }

    @GetMapping("/payments")
    public String createOrder(@SessionAttribute("customer") Customer customer, HttpSession httpSession) {
        /*
        order 미저장 상태 -> payment db저장 후 진행하므로 객체만 생성한다.
        order 객체생성을 위한 inputs맵이 Repository에 있으므로 Repo 클래스 내 메서드를 이용하여 생성하고 리턴해줌
         */
        Order order = orderService.createOrder(customer);
        httpSession.setAttribute("order", order);

//        :: HttpSeesion 내 전체 데이터 출력
//        Enumeration<String> enum_session = httpSession.getAttributeNames();
//        while(enum_session.hasMoreElements()) {
//            String key = enum_session.nextElement();
//            Object val = httpSession.getAttribute(key);
//            System.out.println("key : " + key + ", " + val);
//        }
        return "payments/paymentHome";
    }



//    @ResponseBody
//    @PostMapping("/orders/orderForm")
//    public String selectMenu(HttpServletRequest request) {
//        String str = request.getParameter("pk");
//        Long pk = Long.valueOf(str);
//        ArrayList<Input> inputs = orderService.selectMenu(pk);
//        for (int i = 0; i < inputs.size(); i++) {
//            System.out.println(inputs.get(i));
//        }
//        return "";
//    }


}
