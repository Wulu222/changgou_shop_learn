package com.changgou.service.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo")
@RestController // @Controller + @ResponseBody
public class Democontroller {

    @GetMapping("/test")
    public String hello() {
        return "hello,word";
    }
}
