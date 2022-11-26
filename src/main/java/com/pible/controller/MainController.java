package com.pible.controller;

import com.pible.model.dto.BoardDto;
import com.pible.model.dto.ReplyDto;
import com.pible.service.BoardService;
import com.pible.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final BoardService boardServiceImpl;
    private final ReplyService replyServiceImpl;

    @PostMapping("/board")
    public BoardDto saveBoard(@RequestBody BoardDto boardDto){
        System.out.println(boardDto.getTitle());
        return boardServiceImpl.saveBoard(boardDto);
    }

    @PostMapping("/reply")
    public ReplyDto saveReply(@RequestBody ReplyDto replyDto){
        System.out.println(replyDto.getComment());
        return replyServiceImpl.saveReply(replyDto);
    }
}
