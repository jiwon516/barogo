package com.jiwon.example.barogo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeliveryDto {

    private Long id;

    private int orderDate;

    private int status;

    @NotEmpty(message = "배달주소는 필수 항목입니다.")
    private String address;

    private String addressDetail;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
