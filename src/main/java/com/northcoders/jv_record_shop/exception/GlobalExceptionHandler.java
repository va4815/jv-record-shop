package com.northcoders.jv_record_shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ServerError error) {
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleAlbumNotFoundException(AlbumNotFoundException e) {
        ServerError serverError = new ServerError("Album NOT found", HttpStatus.NOT_FOUND, e);
        return buildResponseEntity(serverError);
    }

}
