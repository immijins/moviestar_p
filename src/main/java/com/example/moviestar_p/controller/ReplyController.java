package com.example.moviestar_p.controller;

import com.example.moviestar.dto.PageRequestDTO;
import com.example.moviestar.dto.PageResponseDTO;
import com.example.moviestar.dto.ReplyDTO;
import com.example.moviestar.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController // 메서드의 리턴 값 JSON으로 처리
@RequestMapping("api/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    // 생성
    @Operation(summary = "Replies Post", description = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO,
                                      BindingResult bindingResult) throws BindException {
        log.info(replyDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String, Long> resultMap = new HashMap<>();
        Long rno = replyService.register(replyDTO);
        resultMap.put("rno", rno);

        return resultMap;
    }

    // 목록
    @Operation(summary = "Replies of Movie", description = "GET 방식으로 특정 게시물의 댓글 목록")
    @GetMapping(value = "/list/{no}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("no") Long bno, PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfMovie(bno, pageRequestDTO);
        return responseDTO;
    }

    // 읽기
    @Operation(summary = "Read Reply", description = "GET 방식으로 특정 댓글 조회")
    @GetMapping(value = "/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno) {
        ReplyDTO replyDTO = replyService.read(rno);
        return replyDTO;
    }

    // 수정
    @Operation(summary = "Modify Reply", description = "PUT 방식으로 특정 댓글 수정")
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO) {
        replyDTO.setRno(rno);
        replyService.modify(replyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }

    // 삭제
    @Operation(summary = "Delete Reply", description = "DELETE 방식으로 특정 댓글 삭제")
    @DeleteMapping(value = "/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno) {
        replyService.remove(rno);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }
}
