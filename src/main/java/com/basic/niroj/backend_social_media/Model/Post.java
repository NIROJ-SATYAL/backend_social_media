package com.basic.niroj.backend_social_media.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String image;
    private String video;


    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> likes = new ArrayList<>();



    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne()
    private User user;

    private LocalDateTime createdat;





}
