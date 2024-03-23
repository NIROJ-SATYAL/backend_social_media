package com.basic.niroj.backend_social_media.Exception;

import com.basic.niroj.backend_social_media.payload.ApiResponse;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalException {



    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFoundException e)
    {
        return  new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> badCredentials(BadCredentialsException e){
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UsernameNotFoundException.class)

    public ResponseEntity<ApiResponse> usernameNotFound(UsernameNotFoundException e){
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NullPointerException.class)

    public ResponseEntity<ApiResponse> nullPointer(NullPointerException e){
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)

    public ResponseEntity<ApiResponse> incorrectResultSizeDataAccess(IncorrectResultSizeDataAccessException e){
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
    }

}
