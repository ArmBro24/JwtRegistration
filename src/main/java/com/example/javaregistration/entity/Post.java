package com.example.javaregistration.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"posts\"")
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likesCount;
}
