package com.basic.niroj.backend_social_media.Model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reels {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String video;
    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> likes = new ArrayList<>();

    private LocalDateTime createdAt;


}
