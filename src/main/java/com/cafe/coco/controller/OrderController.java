package com.cafe.coco.controller;

import com.cafe.coco.domain.Drink;
import com.cafe.coco.domain.Order;
import com.cafe.coco.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

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
    public String orderForm(Model model) {
        List<Drink> menus = orderService.menu();
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
//    }

    @ResponseBody
    @PostMapping("/orders/orderForm")
    public HashMap<String, Object> selectMenu(@RequestBody String str) {
        Long pk = Long.valueOf(str);
        HashMap<String, Object> send = orderService.selectMenu(pk);
        return send;
    }

    @GetMapping("/payments")
    public String createOrder(HttpSession httpSession) {
        Order order = orderService.createOrder();
        httpSession.setAttribute("order", order);
        Enumeration<String> enum_session = httpSession.getAttributeNames();

//        :: HttpSeesion 내 전체 데이터 출력
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
