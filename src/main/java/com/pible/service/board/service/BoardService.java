package com.pible.service.board.service;

import com.pible.service.board.model.BoardDto;

import java.util.List;

public interface BoardService {
    BoardDto saveBoard(BoardDto boardDto);
    boolean deleteBoard(Long boardId);
    BoardDto modifyBoard(Long boardId, BoardDto boardDto);
    boolean increaseLikeCountOfBoard(Long boardId);
    List<BoardDto> getBoardListByTag(String tag);

}
