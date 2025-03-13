package com.jiwon.example.barogo.entity;

import com.jiwon.example.barogo.dto.DeliveryDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int orderDate;

    private int status;

    private String address;

    private String addressDetail;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    public void changeAddress(DeliveryDto dto){
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
    }

    public void setId(long l) {
    }

    public void setStatus(int i) {
    }
}
