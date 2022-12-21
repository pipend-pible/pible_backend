package com.pible.domain.board.controller;

import com.pible.domain.board.model.BoardDto;
import com.pible.domain.board.model.BoardRes;
import com.pible.domain.board.service.BoardService;
import com.pible.domain.claim.board.model.BoardClaimDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public BoardRes saveBoard(@RequestBody @Valid BoardDto boardDto){
        return boardService.saveBoard(boardDto);
    }

    @GetMapping("/{board_id}")
    public BoardRes getBoard(@PathVariable(value = "board_id") Long boardId){
        return boardService.getBoard(boardId);
    }

    @PostMapping("/delete/{board_id}")
    public boolean deleteBoard(@PathVariable(value = "board_id") Long boardId){
        return boardService.deleteBoard(boardId);
    }

    @PostMapping("/modify/{board_id}")
    public BoardRes modifyBoard(@PathVariable(value = "board_id") Long boardId, @RequestBody @Valid BoardDto boardDto){
        return boardService.modifyBoard(boardId, boardDto);
    }

    @PostMapping("/like/{board_id}")
    public boolean increaseLikeCountOfBoard(@PathVariable(value = "board_id") Long boardId){
        return boardService.increaseLikeCountOfBoard(boardId);
    }

    @GetMapping("/tag/{tag}")
    public List<BoardRes> getBoardListByTag(@PathVariable String tag){
        return boardService.getBoardListByTag(tag);
    }

    @PostMapping("/claim/{board_id}")
    public boolean claimBoard(@PathVariable(value = "board_id") Long boardId, @RequestBody BoardClaimDto boardClaimDto){
        return boardService.claimBoard(boardId, boardClaimDto);
    }
}
