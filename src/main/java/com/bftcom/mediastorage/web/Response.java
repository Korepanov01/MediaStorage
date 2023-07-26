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

    public static final ResponseEntity<BadResponseBody> OK = new ResponseEntity<>(
            HttpStatus.OK);

    public static final ResponseEntity<BadResponseBody> ENTITY_NOT_FOUND = new ResponseEntity<>(
            new BadResponseBody(
                    HttpStatus.NOT_FOUND.value(),
                    List.of("Записи не существует!")),
            HttpStatus.NOT_FOUND);

    public static final ResponseEntity<BadResponseBody> ENTITY_ALREADY_EXISTS = new ResponseEntity<>(
            new BadResponseBody(
                    HttpStatus.NOT_FOUND.value(),
                    List.of("Такая запись уже существует!")),
            HttpStatus.NOT_FOUND);
}
