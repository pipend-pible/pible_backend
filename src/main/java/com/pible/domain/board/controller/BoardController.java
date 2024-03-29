package com.pible.domain.board.controller;

import com.pible.domain.board.model.BoardDto;
import com.pible.domain.board.model.BoardRes;
import com.pible.domain.board.service.BoardService;
import com.pible.domain.claim.board.model.BoardClaimDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public BoardRes saveBoard(@RequestParam(value = "images", required = false) List<MultipartFile> multipartFileList,
                              BoardDto boardDto) {
        return boardService.saveBoard(multipartFileList, boardDto);
    }

    @GetMapping("/{boardId}")
    public BoardRes getBoard(@PathVariable Long boardId){
        return boardService.getBoard(boardId);
    }

    @PostMapping("/delete/{boardId}")
    public boolean deleteBoard(@PathVariable Long boardId){
        return boardService.deleteBoard(boardId);
    }

    @PostMapping("/modify/{boardId}")
    public BoardRes modifyBoard(@PathVariable Long boardId, @RequestBody BoardDto boardDto){
        return boardService.modifyBoard(boardId, boardDto);
    }

    @PostMapping("/like/{boardId}")
    public boolean increaseLikeCountOfBoard(@PathVariable Long boardId){
        return boardService.increaseLikeCountOfBoard(boardId);
    }

    @GetMapping("/tag/{tag}")
    public List<BoardRes> getBoardListByTag(@PathVariable String tag){
        return boardService.getBoardListByTag(tag);
    }

    @PostMapping("/claim/{boardId}")
    public boolean claimBoard(@PathVariable Long boardId, @RequestBody BoardClaimDto boardClaimDto){
        return boardService.claimBoard(boardId, boardClaimDto);
    }
}
