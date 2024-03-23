package com.basic.niroj.backend_social_media.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AuthResponse {


    private String Message;

    private boolean success;

    private String token;


    public AuthResponse(String userAlreadyExist, boolean b) {
        this.Message=userAlreadyExist;
        this.success=b;
    }
}
