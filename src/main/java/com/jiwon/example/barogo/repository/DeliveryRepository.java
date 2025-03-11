package com.jiwon.example.barogo.repository;

import com.jiwon.example.barogo.entity.Delivery;
import com.jiwon.example.barogo.entity.Member;
import com.jiwon.example.barogo.repository.querydsl.DeliveryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>, DeliveryRepositoryCustom {
}
