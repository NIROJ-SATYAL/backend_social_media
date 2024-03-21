package com.basic.niroj.backend_social_media.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String gender;

    private List<Long> followers;
    private List<Long> following;

}
