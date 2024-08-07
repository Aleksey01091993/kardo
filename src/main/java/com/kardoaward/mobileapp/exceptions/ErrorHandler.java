package com.kardoaward.mobileapp.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(NotFoundException e) {
        log.info("Received error: {}", e.getMessage());
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Элемент не найден")
                .status(HttpStatus.NOT_FOUND.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleUserAlreadyExists(UserAlreadyExistsException e) {
        log.info("Received error: {}", e.getMessage());
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Пользователь уже существует")
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleFailToUpload(FailedToUploadVideoException e) {
        log.info("Received error: {}", e.getMessage());
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Ошибка при загрузке видео")
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
