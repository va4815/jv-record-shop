package com.northcoders.jv_record_shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ResponseErrorObject error) {
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleAlbumNotFoundException(AlbumNotFoundException e) {
        ResponseErrorObject errorObject = new ResponseErrorObject("Album NOT found", HttpStatus.NOT_FOUND, e);
        return buildResponseEntity(errorObject);
    }

}
