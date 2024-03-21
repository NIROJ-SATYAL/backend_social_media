package com.basic.niroj.backend_social_media.Exception;

import com.basic.niroj.backend_social_media.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalException {



    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFoundException e)
    {
        return  new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.NOT_FOUND);
    }

}
