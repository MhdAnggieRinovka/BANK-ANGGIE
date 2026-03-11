package com.bank.assigmenBNI.exceptionHandler;

import com.bank.assigmenBNI.webResponseEntity.WebResponse;
import jakarta.servlet.http.HttpServletRequest;
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
import java.util.StringJoiner;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final RestClient.Builder builder;

    public GlobalExceptionHandler(RestClient.Builder builder) {
        this.builder = builder;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<WebResponse<String>> handleInvalidFormat(HttpMessageNotReadableException ex, HttpServletRequest request) {
        Long start = (Long) request.getAttribute("startTime");
        long duration = (start != null) ? (System.currentTimeMillis() - start) : 0;

        String errorMessage = "Format JSON tidak valid atau Body kosong";

        if (ex.getMessage() != null && ex.getMessage().contains("Date")) {
            errorMessage = "Format tanggal salah. Gunakan yyyy-MM-dd (ex: 2026-03-10)";
        } else if (ex.getMessage() != null && ex.getMessage().contains("Required request body is missing")) {
            errorMessage = "Body request tidak boleh kosong!";
        }

        WebResponse<String> response = WebResponse.<String>builder()
                .status_code(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage) // Pesan jadi dinamis sekarang
                .data(null)
                .build();

        log.error("JSON Error: {} | Duration: {}ms", errorMessage, duration);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        Long start = (Long) request.getAttribute("startTime");
        long duration = (start != null) ? (System.currentTimeMillis() - start) : 0;
        StringJoiner joiner = new StringJoiner(", ");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            joiner.add(errorMessage);
            log.error("Create Data Failed: {} {} {}ms",fieldName,errorMessage,duration);
        });

        WebResponse<Map<String, String>> response = new WebResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Validasi Gagal: "+ joiner.toString(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}