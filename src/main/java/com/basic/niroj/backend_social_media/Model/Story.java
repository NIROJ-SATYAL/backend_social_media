package com.basic.niroj.backend_social_media.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Story
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image;
    @ManyToOne
    private User user;

    @ManyToMany
    @JsonIgnore
    private List<User> seenby = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<User> likedby = new ArrayList<>();

    private LocalDateTime createdat;
}
