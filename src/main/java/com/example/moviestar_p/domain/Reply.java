package com.example.moviestar_p.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reply", indexes = {
        @Index(name="idx_reply_movie_no", columnList = "movie_no")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "movie")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    private String replyText;
    private String replyWriter;
    private int movieLike;

    public void changeText(String text, int like) {
        this.replyText = text;
        this.movieLike = like;
    }
}
