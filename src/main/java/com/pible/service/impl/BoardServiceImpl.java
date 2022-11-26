package com.pible.service.impl;

import com.pible.dao.BoardRepository;
import com.pible.model.dto.BoardDto;
import com.pible.model.entity.BoardEntity;
import com.pible.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    @Override
    public BoardDto saveBoard(BoardDto boardDto){
        boardRepository.save(BoardEntity.builder().title(boardDto.getTitle()).build());
        return boardDto;
    }
}
