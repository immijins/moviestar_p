package com.example.moviestar_p.service;

import com.example.moviestar.dto.PageRequestDTO;
import com.example.moviestar.dto.PageResponseDTO;
import com.example.moviestar.dto.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO); // 생성
    ReplyDTO read(Long rno); // 읽기
    void modify(ReplyDTO replyDTO); // 수정
    void remove(Long rno); // 삭제
    PageResponseDTO<ReplyDTO> getListOfMovie(Long no, PageRequestDTO pageRequestDTO);

}
