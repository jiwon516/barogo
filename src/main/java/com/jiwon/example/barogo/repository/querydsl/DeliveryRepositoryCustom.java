package com.jiwon.example.barogo.repository.querydsl;

import com.jiwon.example.barogo.dto.DeliveryDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeliveryRepositoryCustom {
    @Transactional(readOnly = true)
    List<DeliveryDto> findDeliveryList(Integer startDate, Integer endDate, int status);
}
