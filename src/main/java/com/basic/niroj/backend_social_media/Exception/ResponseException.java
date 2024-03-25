package com.basic.niroj.backend_social_media.Exception;

public class ResponseException extends  RuntimeException{

    String message;

    public ResponseException(String message) {
        super(message);
        this.message = message;
    }
}
