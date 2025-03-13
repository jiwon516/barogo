package com.jiwon.example.barogo.controller;

import com.jiwon.example.barogo.dto.DeliveryDto;
import com.jiwon.example.barogo.global.exception.OrderServiceException;
import com.jiwon.example.barogo.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderApiController {
    private final OrderService orderService;

    // FIXME Throw 분리, 상태변경(update) -> Domain(Entity)으로 옮기기, 테스트코드 작성하기
    // FIXME 조회 .orElseThrow(() -> new OrderSearchException("존재하지 않는 주문정보 입니다."));

    // 주문조회 api
    @GetMapping("/api/delivery")
    public ResponseEntity<?> orderSearch(@RequestParam(value = "start", required = false) Integer start,
                                                         @RequestParam(value = "end", required = false) Integer end,
                                                         @RequestParam(value = "status", required = false) int status) {

        assert (start > 0);
        assert (end > 0);

        if (start == null || end == null) {
            return ResponseEntity.badRequest().body(Collections.singletonList("조회 기간을 올바르게 입력해야 합니다."));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            LocalDate startDate = LocalDate.parse(start.toString(), formatter);
            LocalDate endDate = LocalDate.parse(end.toString(), formatter);

            if (startDate.isAfter(endDate)) {
                throwOrderSearchException("조회 시작기간이 종료기간 보다 큽니다.");
            }

            long daysBetween = startDate.until(endDate).getDays();
            if (daysBetween >= 3) {
                throwOrderSearchException("한번에 조회 가능한 기간은 최대 3일입니다.");
            }
        } catch (OrderServiceException e) {
            throwOrderSearchException("날짜 형식이 올바르지 않습니다.");
        } catch (Exception e){
            throw new RuntimeException("예기치 못한 문제가 발생했습니다.");
        }

        return ResponseEntity.ok(orderService.orderSearch(start, end, status));
    }

    private void throwOrderSearchException(String message) {
        throw new OrderServiceException(message);
    }

    // 주소변경 api
    @PatchMapping("/api/delivery")
    public ResponseEntity<?> updateDelivery(@RequestBody @Valid DeliveryDto deliveryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();

            bindingResult.getFieldErrors().forEach(error ->
                    errors.add(error.getDefaultMessage())
            );

            return ResponseEntity.badRequest().body(errors);
        }

        return orderService.updateAddress(deliveryDto);
    }
}
