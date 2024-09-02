package com.asPasa.testTask.api.exceptionHandlers;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInvalidRequest(ApplicationException exception, WebRequest request){
        ErrorResponse response= new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
