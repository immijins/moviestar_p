package com.example.moviestar_p.repository;

import com.example.moviestar.domain.Movie;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Log4j2
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    // 생성
    @Test
    public void testInsert() {
        for (int i = 1; i <= 30; i++) {
            Movie movie = Movie.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .build();
            Movie result = movieRepository.save(movie);
            log.info("NO : " + result.getNo());
        }
    }

    // 조회
    @Test
    public void testSelect() {
        Long no = 2L;
        Optional<Movie> result = movieRepository.findById(no);
        Movie movie = result.orElseThrow();
        log.info(movie);
    }

    // 수정
    @Test
    public void testUpdate() {
        Long no = 3L;
        Optional<Movie> result = movieRepository.findById(no);
        Movie movie = result.orElseThrow();
        movie.change("title Update...", "content Update...", "");
        movieRepository.save(movie);
    }

    // 삭제
    @Test
    public void deleteTest() {
        Long no = 1L;
        movieRepository.deleteById(no);
    }
}