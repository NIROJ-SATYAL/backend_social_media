package com.basic.niroj.backend_social_media.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserReponse {

    private String message;

    private Boolean success;
    private Object data;
}
