package com.bank.assigmenBNI.exceptionHandler;

import com.bank.assigmenBNI.webResponseEntity.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClient;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final RestClient.Builder builder;

    public GlobalExceptionHandler(RestClient.Builder builder) {
        this.builder = builder;
    }

    // Menangani error format JSON (termasuk tanggal yang salah format)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<WebResponse<String>> handleInvalidFormat(HttpMessageNotReadableException ex) {
        WebResponse<String> response = WebResponse.<String>builder()
                .status_code(HttpStatus.BAD_REQUEST.value())
                .message("Format benar adalah yyyy-MM-dd (ex : 2026-03-10).")
                .data(null)
                .build();
        log.error(new Date()+ " Format Tanggal Salah yyyy-MM-dd"+" "+ HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            log.error(new Date()+ " Create Data Failed: "+ fieldName+ " "+ errorMessage + " "+ HttpStatus.BAD_REQUEST.value());
        });

        WebResponse<Map<String, String>> response = new WebResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Validasi Gagal",
                errors
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}