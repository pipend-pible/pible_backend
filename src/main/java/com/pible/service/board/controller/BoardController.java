package com.pible.service.board.controller;

import com.pible.service.board.model.BoardDto;
import com.pible.service.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public BoardDto saveBoard(@RequestBody @Valid BoardDto boardDto){
        return boardService.saveBoard(boardDto);
    }

}
