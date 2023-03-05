package com.pible.domain.board.service;

import com.pible.domain.board.model.BoardDto;
import com.pible.domain.board.model.BoardRes;
import com.pible.domain.claim.board.model.BoardClaimDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    BoardRes saveBoard(List<MultipartFile> multipartFileList, BoardDto boardDto);
    BoardRes getBoard(Long boardId);
    boolean deleteBoard(Long boardId);
    BoardRes modifyBoard(Long boardId, BoardDto boardDto);
    boolean increaseLikeCountOfBoard(Long boardId);
    List<BoardRes> getBoardListByTag(String tag);
    boolean claimBoard(Long boardId, BoardClaimDto boardClaimDto);

}
