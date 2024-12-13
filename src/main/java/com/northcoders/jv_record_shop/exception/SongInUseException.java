package com.northcoders.jv_record_shop.exception;

public class SongInUseException extends RuntimeException {

    public SongInUseException(String message) {
        super(message);
    }
}
