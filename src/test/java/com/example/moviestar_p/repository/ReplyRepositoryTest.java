package com.example.moviestar_p.repository;

import com.example.moviestar.domain.Movie;
import com.example.moviestar.domain.Reply;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



@SpringBootTest
@Log4j2
class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository repository;

    @Test
    public void testInsert() {
        for (int i = 0; i <= 20; i++) {
            Reply reply = Reply.builder()
                    .replyText("댓글 내용")
                    .replyWriter("댓글 작성자")
                    .movieLike(5)
                    .movie(Movie.builder().no(32L).build())
                    .build();
            Reply result = repository.save(reply);
            log.info(result);
        }
    }

    @Test
    @Transactional
    public void listOfMovieTest() {
        Long no = 3L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
        Page<Reply> replies = repository.listOfMovie(no, pageable);
        replies.getContent().forEach(log::info);
    }
}