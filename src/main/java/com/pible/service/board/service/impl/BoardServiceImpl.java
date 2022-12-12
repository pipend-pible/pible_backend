package com.pible.service.board.service.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.TagEntity;
import com.pible.common.exception.CustomException;
import com.pible.service.board.dao.BoardRepository;
import com.pible.service.board.mapper.BoardMapper;
import com.pible.service.board.model.BoardDto;
import com.pible.service.board.service.BoardService;
import com.pible.service.category.board.dao.BoardCategoryRepository;
import com.pible.service.channel.dao.ChannelRepository;
import com.pible.service.tag.dao.TagRepository;
import com.pible.service.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    private final BoardMapper boardMapper = BoardMapper.INSTANCE;

    @Override
    @Transactional
    public BoardDto saveBoard(BoardDto boardDto) {
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

    @Override
    @Transactional
    public boolean deleteBoard(Long boardId) {
        boardRepository.findById(boardId).orElseThrow(() -> new CustomException("")).delete();
        return true;
    }

    @Override
    @Transactional
    public BoardDto modifyBoard(Long boardId, BoardDto boardDto) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(""));
        boardMapper.updateFromDto(boardDto, boardEntity);
        return boardMapper.entityToDto(boardEntity);
    }

    @Override
    @Transactional
    public boolean increaseLikeCountOfBoard(Long boardId) {
        boardRepository.findById(boardId).orElseThrow(() -> new CustomException("")).like();
        return true;
    }

    @Override
    public List<BoardDto> getBoardListByTag(String tag) {
        TagEntity tagEntity = tagRepository.findByTag(tag).orElseThrow(() -> new CustomException(""));


        return null;
    }
}
