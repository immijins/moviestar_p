package com.example.moviestar_p.service;

import com.example.moviestar.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class ReplyServiceImplTest {
    @Autowired
    private ReplyService replyService;

    @Test
    public void testRegister() {
        ReplyDTO reply = ReplyDTO.builder()
                .no(32L)
                .replyText("댓글 테스트")
                .replyWriter("작성자1")
                .build();
        Long no = replyService.register(reply);
        log.info(no);
    }

    @Test
    public void testRead() {
        Long rno = 2L;
        ReplyDTO result = replyService.read(rno);
        log.info(result);
    }

    @Test
    public void testModify() {
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(3L)
                .replyText("댓글 수정")
                .movieLike(5)
                .build();
        replyService.modify(replyDTO);
    }

    @Test
    public void testDelete() {
        Long rno = 1L;
        replyService.remove(rno);
    }
}