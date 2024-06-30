package com.example.moviestar_p.service;

import com.example.moviestar.dto.MovieDTO;
import com.example.moviestar.dto.PageRequestDTO;
import com.example.moviestar.dto.PageResponseDTO;

public interface MovieService {
    Long register(MovieDTO movieDTO); // 생성
    PageResponseDTO<MovieDTO> list(PageRequestDTO pageRequestDTO); // 목록
    MovieDTO readOne(Long no); // 읽기
    void modify(MovieDTO movieDTO); // 수정
    void remove(Long no); // 삭제
}
