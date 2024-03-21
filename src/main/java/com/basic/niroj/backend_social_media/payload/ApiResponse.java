package com.basic.niroj.backend_social_media.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Getter
@Setter
public class ApiResponse {


    private String message;
    private Boolean success;


    public ApiResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
