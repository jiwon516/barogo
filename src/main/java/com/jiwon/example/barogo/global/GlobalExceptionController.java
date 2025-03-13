package com.jiwon.example.barogo.global;

import com.jiwon.example.barogo.global.exception.OrderServiceException;
import com.jiwon.example.barogo.global.exception.UserServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<String> userServiceExceptionException(UserServiceException e){
        return ResponseEntity
                .badRequest().body(e.getMessage());
    }

    @ExceptionHandler(OrderServiceException.class)
    public ResponseEntity<String> orderFindExceptionException(OrderServiceException e){
        return ResponseEntity
                .badRequest().body(e.getMessage());
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<List<String>> runtimeException(RuntimeException e){
        return ResponseEntity
                .badRequest().body(Collections.singletonList(e.getMessage()));
    }
}
