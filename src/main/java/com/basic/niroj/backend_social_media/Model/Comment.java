package com.basic.niroj.backend_social_media.Model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;


    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    @ManyToMany
    private List<User> likedby=new ArrayList<>();

    private LocalDateTime createdat;
}
