package com.cafe.coco.controller;

import com.cafe.coco.domain.Drink;
import com.cafe.coco.domain.Input;
import com.cafe.coco.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
    public String orderForm(Model model) {
        List<Drink> menus = orderService.menu();
        System.out.println(menus.get(2));
        model.addAttribute("menus", menus);
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
//
//    }

    @ResponseBody
    @PostMapping("/orders/orderForm")
    public void selectMenu(HttpServletRequest request) {
        String str = request.getParameter("pk");
        Long pk = Long.valueOf(str);
        ArrayList<Input> inputs = orderService.selectMenu(pk);
        for (int i = 0; i < inputs.size(); i++) {
            System.out.println(inputs.get(i));
        }

    }


}
