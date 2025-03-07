package com.jiwon.example.barogo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountDto {
    @NotEmpty(message = "이름은 필수 항목입니다.")
    private String userName;
    @NotEmpty(message = "아이디는 필수 항목입니다.")
    private String userId;
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    @Pattern(regexp = "^(?=(.*[A-Za-z]){1,})(?=(.*\\d){1,})(?=(.*[$@$!%*#?&.,~()-_=+₩]){1,}).{12,}$",
                message = "비밀번호는 영어 대문자, 소문자, 숫자, 특수문자 중 3종류 이상을 포함하고, 12자리 이상이어야 합니다.")
    private String userPassword;
}
