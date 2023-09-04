package com.bftcom.mediastorage.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Responses {

    @Data
    @AllArgsConstructor
    private static class BadResponseBody {
        private final LocalDateTime timestamp = LocalDateTime.now();
        private List<String> errors;
    }

    public static final ResponseEntity<?> OK = ResponseEntity.ok().build();

    public static final ResponseEntity<?> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    public static final ResponseEntity<?> NOT_FOUND = notFound("Не найдено");

    public static ResponseEntity<BadResponseBody> getResponse(String message, HttpStatus httpStatus) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.hasText(message)) {
            errors.add(message);
        }

        return new ResponseEntity<>(
                new BadResponseBody(errors),
                httpStatus);
    }

    public static ResponseEntity<BadResponseBody> badRequest(String message) {
        return getResponse(message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<BadResponseBody> notFound(String message) {
        return getResponse(message, HttpStatus.NOT_FOUND);
    }
}
