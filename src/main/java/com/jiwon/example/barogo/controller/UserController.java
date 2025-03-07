package com.jiwon.example.barogo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    //로그인
    @GetMapping({"/","/login"})
    public String login() {
        return "user/login";
    }

    //회원가입
    @GetMapping({"registration"})
    public String registration() {
        return "user/registration";
    }
}
