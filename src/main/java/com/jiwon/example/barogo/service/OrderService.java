package com.jiwon.example.barogo.service;


import com.jiwon.example.barogo.dto.DeliveryDto;
import com.jiwon.example.barogo.entity.Delivery;
import com.jiwon.example.barogo.entity.Member;
import com.jiwon.example.barogo.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final DeliveryRepository deliveryRepository;

    @Transactional(readOnly = true)
    public List<DeliveryDto> orderSearch(int start, int end, int status) {
        return deliveryRepository.findDeliveryList(start, end, status);
    }

    public ResponseEntity<?> updateAddress(DeliveryDto dto) {
        Optional<Delivery> delivery = deliveryRepository.findById(dto.getId());
        if(delivery.isEmpty()){
            return ResponseEntity.badRequest().body(Collections.singletonList("존재하지 않는 주문정보 입니다."));
        }
        if(delivery.get().getStatus() != 0){
            return ResponseEntity.badRequest().body(Collections.singletonList("주소변경이 불가한 상태입니다."));
        }

        delivery.get().setAddress(dto.getAddress());
        delivery.get().setAddressDetail(dto.getAddressDetail());

        deliveryRepository.save(delivery.get());

        return ResponseEntity.ok("success");
    }
}