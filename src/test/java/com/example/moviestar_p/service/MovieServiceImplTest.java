package com.example.moviestar_p.service;

import com.example.moviestar.dto.MovieDTO;
import com.example.moviestar.dto.PageRequestDTO;
import com.example.moviestar.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class MovieServiceImplTest {
    @Autowired
    private MovieService movieService;

    @Test
    public void testRegister() {
        MovieDTO movie = MovieDTO.builder()
                .title("타이틀")
                .content("내용")
                .build();
        Long no = movieService.register(movie);
        log.info(no);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("t")
                .keyword("t")
                .build();

        PageResponseDTO<MovieDTO> movieDTOPageResponseDTO = movieService.list(pageRequestDTO);

        log.info(movieDTOPageResponseDTO);
        log.info("1) 페이지 번호 : " + movieDTOPageResponseDTO.getPage());
        log.info("2) 전체 게시물 수 : " + movieDTOPageResponseDTO.getTotal());
        log.info("3) 현재 페이지에 출력될 게시물을 반복문을 이용해서 순서대로 출력" );
        movieDTOPageResponseDTO.getDtoList().forEach(movieDTO -> {log.info(movieDTO); });
    }

    @Test
    public void testReadOne() {
        Long no = 2L;
        MovieDTO result = movieService.readOne(no);
        log.info(result);
    }

    @Test
    public void testModify() {
        MovieDTO movieDTO = MovieDTO.builder()
                .no(2L)
                .title("update2")
                .content("update2")
                .imagePath("")
                .build();
        movieService.modify(movieDTO);
    }

    @Test
    public void testDelete() {
        Long no = 1L;
        movieService.remove(no);
    }
}