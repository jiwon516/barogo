package com.jiwon.example.barogo.controller;

import com.jiwon.example.barogo.dto.UserDto;
import com.jiwon.example.barogo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signUp(@Valid UserDto userDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "user/login";
        }

        userService.createUser(userDto);

        return "redirect:/";
    }
}
