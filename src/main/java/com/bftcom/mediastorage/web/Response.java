package com.bftcom.mediastorage.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public class Response {

    private static class BadResponseBody {

        private final LocalDateTime timestamp = LocalDateTime.now();

        private final int status;

        private final List<String> errors;

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public List<String> getErrors() {
            return errors;
        }

        public BadResponseBody(int status, List<String> errors) {
            this.status = status;
            this.errors = errors;
        }
    }

    public static ResponseEntity<BadResponseBody> TagNameAlreadyExists = new ResponseEntity<>(
            new BadResponseBody(
                    HttpStatus.BAD_REQUEST.value(),
                    List.of("Тег уже существует!")),
            HttpStatus.BAD_REQUEST);


    public static ResponseEntity<BadResponseBody> TagNotFound = new ResponseEntity<>(
            new BadResponseBody(
                    HttpStatus.NOT_FOUND.value(),
                    List.of("Тег не найден!")),
            HttpStatus.NOT_FOUND);

    public static ResponseEntity<BadResponseBody> RoleNameAlreadyExists = new ResponseEntity<>(
            new BadResponseBody(
                    HttpStatus.BAD_REQUEST.value(),
                    List.of("Роль уже существует!")),
            HttpStatus.BAD_REQUEST);


    public static ResponseEntity<BadResponseBody> RoleNotFound = new ResponseEntity<>(
            new BadResponseBody(
                    HttpStatus.NOT_FOUND.value(),
                    List.of("Роль не найдена!")),
            HttpStatus.NOT_FOUND);
}
