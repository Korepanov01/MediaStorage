package com.bftcom.mediastorage.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

        return getResponse(message, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<BadResponseBody> getEntityAlreadyExists() {
        return getEntityNotFound("Такая запись уже существует!");
    }

    public static ResponseEntity<BadResponseBody> getEntityAlreadyExists(String message) {
        if (message == null) {
            return getEntityAlreadyExists();
        }

        return getResponse(message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<BadResponseBody> getFileTooBig(String message) {
        return getResponse(message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<BadResponseBody> getBadFileReading() {
        return getResponse("Произошла ошибка при чтении файла", HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<BadResponseBody> getResponse(String message, HttpStatus httpStatus) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.hasText(message)) {
            errors.add(message);
        }

        return new ResponseEntity<>(
                new BadResponseBody(errors),
                httpStatus);
    }
}
