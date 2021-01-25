package com.cafe.coco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpSession httpSession) {
        httpSession.invalidate();
        return "home";
    }
}
