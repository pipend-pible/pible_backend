package com.pible.service.board.controller;

import com.pible.service.board.model.BoardDto;
import com.pible.service.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @PostMapping("/delete/{board_id}")
    public boolean deleteBoard(@PathVariable(value = "board_id") @NotNull Long boardId){
        return boardService.deleteBoard(boardId);
    }

    @PostMapping("/modify/{board_id}")
    public BoardDto modifyBoard(@PathVariable(value = "board_id") @NotNull Long boardId, @RequestBody @Valid BoardDto boardDto){
        return boardService.modifyBoard(boardId, boardDto);
    }

    @PostMapping("/like/{board_id}")
    public boolean increaseLikeCountOfBoard(@PathVariable(value = "board_id") @NotNull Long boardId){
        return boardService.increaseLikeCountOfBoard(boardId);
    }

    @GetMapping("/tag/{tag}")
    public List<BoardDto> getBoardListByTag(@PathVariable String tag){
        return boardService.getBoardListByTag(tag);
    }
}
