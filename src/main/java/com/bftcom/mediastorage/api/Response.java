package com.bftcom.mediastorage.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public class Response {

    @Data
    @AllArgsConstructor
    private static class BadResponseBody {
        private final LocalDateTime timestamp = LocalDateTime.now();
        private List<String> errors;
    }

    private static final ResponseEntity<BadResponseBody> OK = new ResponseEntity<>(
            HttpStatus.OK);

    public static ResponseEntity<BadResponseBody> getOk() {
        return OK;
    }

    public static ResponseEntity<BadResponseBody> getEntityNotFound() {
        return getEntityNotFound("Записи не существует!");
    }

    public static ResponseEntity<BadResponseBody> getEntityNotFound(String message) {
        if (message == null) {
            return getEntityNotFound();
        }

        return new ResponseEntity<>(
                new BadResponseBody(List.of(message)),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<BadResponseBody> getEntityAlreadyExists() {
        return getEntityNotFound("Такая запись уже существует!");
    }

    public static ResponseEntity<BadResponseBody> getEntityAlreadyExists(String message) {
        if (message == null) {
            return getEntityAlreadyExists();
        }

        return new ResponseEntity<>(
                new BadResponseBody(List.of(message)),
                HttpStatus.BAD_REQUEST);
    }
}
