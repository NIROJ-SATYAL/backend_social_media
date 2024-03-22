package com.basic.niroj.backend_social_media.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @ElementCollection
    private List<Long> followers = new ArrayList<>();

    @ElementCollection
    private List<Long> following = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @JsonIgnore
    private List<Post> posts = new ArrayList<>();


    @ManyToMany
    private List<Post> savedpost= new ArrayList<>();

}
