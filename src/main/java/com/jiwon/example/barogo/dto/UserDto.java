package com.jiwon.example.barogo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotEmpty(message = "이름은 필수 항목입니다.")
    private String userName;
    @NotEmpty(message = "아이디는 필수 항목입니다.")
    private String userId;
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String userPassword;
}
