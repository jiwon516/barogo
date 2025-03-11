package com.jiwon.example.barogo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeliveryDto {

    private Long id;

    private int orderDate;

    private int status;

    private String address;

    private String addressDetail;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
