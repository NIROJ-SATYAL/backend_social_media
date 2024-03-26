package com.basic.niroj.backend_social_media.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String message;

    private String image;

    @ManyToOne
    private User sender;

    @ManyToOne
    private Chat chat;



    private LocalDateTime timestamp;
}
