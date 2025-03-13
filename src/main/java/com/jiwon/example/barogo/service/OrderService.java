package com.jiwon.example.barogo.service;


import com.jiwon.example.barogo.dto.DeliveryDto;
import com.jiwon.example.barogo.entity.Delivery;
import com.jiwon.example.barogo.global.exception.OrderServiceException;
import com.jiwon.example.barogo.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final DeliveryRepository deliveryRepository;

    @Transactional(readOnly = true)
    public List<DeliveryDto> orderSearch(int start, int end, int status) {
        return deliveryRepository.findDeliveryList(start, end, status);
    }
    
    @Transactional
    public ResponseEntity<?> updateAddress(DeliveryDto dto) {
        Delivery delivery = findDelivery(dto);

        validate(delivery);
        delivery.changeAddress(dto);
        deliveryRepository.save(delivery);

        Map<String, String> response = new HashMap<>();
        response.put("responseText", "정상적으로 변경되었습니다.");

        return ResponseEntity.ok(response);
    }

    private Delivery findDelivery(DeliveryDto dto) {
        return deliveryRepository.findById(dto.getId())
                .orElseThrow(() -> new OrderServiceException("존재하지 않는 주문정보 입니다."));
    }

    private void validate(Delivery delivery) {
        if(delivery.getStatus() != 1){
            throw new OrderServiceException("주소변경이 불가한 상태입니다.");
        }
    }
}