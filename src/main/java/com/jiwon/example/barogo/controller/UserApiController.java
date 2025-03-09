package com.jiwon.example.barogo.controller;

import com.jiwon.example.barogo.dto.UserAccountDto;
import com.jiwon.example.barogo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    // 회원가입 api
    @PostMapping("/api/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserAccountDto userAccountDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();

            bindingResult.getFieldErrors().forEach(error ->
                    errors.add(error.getDefaultMessage())
            );

            return ResponseEntity.badRequest().body(errors);
        }

        boolean isNew = userService.createUser(userAccountDto);
        if(!isNew) {
            return ResponseEntity.badRequest().body(Collections.singletonList("사용중인 아이디 입니다."));
        }

        return ResponseEntity.ok("success");
    }

    // 로그인 api
    @PostMapping("/api/signin")
    public ResponseEntity<?> signIn(@RequestBody UserAccountDto userAccountDto) {
        return userService.signIn(userAccountDto.getUserId(), userAccountDto.getUserPassword());
    }
}
