package com.bftcom.mediastorage.web;

import com.bftcom.mediastorage.exception.TagAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BadResponses {

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

    public static ResponseEntity<BadResponseBody> TagAlreadyExists = new ResponseEntity<>(
            new BadResponseBody(
                    HttpStatus.BAD_REQUEST.value(),
                    List.of("Tag already exists!")),
            HttpStatus.BAD_REQUEST);
}
