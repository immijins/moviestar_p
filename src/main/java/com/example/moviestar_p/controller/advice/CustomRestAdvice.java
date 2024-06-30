package com.example.moviestar_p.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Log4j2
@RestControllerAdvice
public class CustomRestAdvice {
    // 유효성 검사의 결과를 JSON으로 반환하기 위함
    // 컨트롤러에서 발생하는 예외에 대해 JSON과 같은 응답 메시지를 생성해서 보낼 수 있음

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException(BindException e) {
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        if(e.hasErrors()) {
            BindingResult bindingResult = e.getBindingResult();

            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getCode());
            });
        }
        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handlerFKException(Exception e) {
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("time", "" + System.currentTimeMillis());
        errorMap.put("msg", "constraint fails");

        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handlerNoSuchElementException(Exception e) {
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("time", "" + System.currentTimeMillis());
        errorMap.put("msg", "No Such Element Exception");

        return ResponseEntity.badRequest().body(errorMap);
    }
}
