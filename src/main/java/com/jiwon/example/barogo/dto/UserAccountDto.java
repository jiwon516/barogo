package com.jiwon.example.barogo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountDto {
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 한글과 영어만 입력 가능합니다.")
    @NotEmpty(message = "이름은 필수 항목입니다.")
    private String userName;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영어와 숫자만 입력 가능합니다.")
    @NotEmpty(message = "아이디는 필수 항목입니다.")
    private String userId;

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    @Pattern(regexp = "^(?=(.*[A-Za-z]){1,})(?=(.*\\d){1,})(?=(.*[$@$!%*#?&.,~()-_=+₩]){1,}).{12,}$",
                message = "비밀번호는 영어 대문자, 소문자, 숫자, 특수문자 중 3종류 이상을 포함하고, 12자리 이상이어야 합니다.")
    private String userPassword;
}
