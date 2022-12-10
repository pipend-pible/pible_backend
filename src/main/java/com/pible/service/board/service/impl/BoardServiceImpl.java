package com.pible.service.board.service.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.service.board.dao.BoardRepository;
import com.pible.service.board.mapper.BoardMapper;
import com.pible.service.board.model.BoardDto;
import com.pible.service.board.service.BoardService;
import com.pible.service.category.board.dao.BoardCategoryRepository;
import com.pible.service.channel.dao.ChannelRepository;
import com.pible.service.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    private final BoardMapper boardMapper = BoardMapper.INSTANCE;

    @Override
    @Transactional
    public BoardDto saveBoard(BoardDto boardDto){
        BoardEntity boardEntity = boardMapper.dtoToEntity(boardDto);

        boardEntity.setRelation(
                channelRepository.getReferenceById(boardDto.getChannelId()),
                userRepository.getReferenceById(boardDto.getUserId()),
                boardCategoryRepository.getReferenceById(boardDto.getBoardCategoryId())
        );

        return boardMapper.entityToDto(
                boardRepository.save(boardEntity)
        );
    }
}
