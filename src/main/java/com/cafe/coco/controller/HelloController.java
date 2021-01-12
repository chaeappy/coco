package com.cafe.coco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    @GetMapping("hello-string")
    // http의 body에 data(return 부분)를 직접 넣어주겠다는 의미
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {

        return "hello" + name;
    }

}

