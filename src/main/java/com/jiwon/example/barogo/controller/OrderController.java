package com.jiwon.example.barogo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    //주문 조회
    @GetMapping({"/delivery"})
    public String delivery() {
        return "order/delivery";
    }
}
