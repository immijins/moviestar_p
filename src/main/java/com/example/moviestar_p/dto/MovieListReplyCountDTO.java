package com.example.moviestar_p.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovieListReplyCountDTO {
    private Long no;
    private String title;
    private String writer;
    private int movieLike;
    private LocalDateTime regDate;
    private Long replyCount;
}
