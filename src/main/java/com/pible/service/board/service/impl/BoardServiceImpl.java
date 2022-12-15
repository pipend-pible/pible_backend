package com.pible.service.board.service.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.TagEntity;
import com.pible.common.entity.BoradTagMappingEntity;
import com.pible.common.exception.CustomException;
import com.pible.service.board.dao.BoardRepository;
import com.pible.service.board.mapper.BoardMapper;
import com.pible.service.board.model.BoardDto;
import com.pible.service.board.service.BoardService;
import com.pible.service.category.board.dao.BoardCategoryRepository;
import com.pible.service.channel.dao.ChannelRepository;
import com.pible.service.mapping.dao.BoardTagMappingRepository;
import com.pible.service.tag.dao.TagRepository;
import com.pible.service.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    private final BoardTagMappingRepository boardTagMappingRepository;
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

        boardEntity = boardRepository.save(boardEntity);

        for(String tag : boardDto.getTagList()) {
            TagEntity tagEntity = tagRepository.findByTag(tag).orElseGet(
                    () -> tagRepository.save(TagEntity.builder().tag(tag).build())
            );

            boardTagMappingRepository.save(BoradTagMappingEntity.builder()
                    .boardEntity(boardEntity)
                    .tagEntity(tagEntity)
                    .build());
        }

        return boardMapper.entityToDto(boardEntity);
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
        return boardTagMappingRepository.findAllByTagEntity(
                tagRepository.findByTag(tag).orElseThrow(() -> new CustomException("")))
                .stream()
                .map((boradTagMappingEntity -> {
                    BoardEntity boardEntity = boradTagMappingEntity.getBoardEntity();
                    Hibernate.initialize(boardEntity);
                    return boardMapper.entityToDto(boardEntity);
                })).collect(Collectors.toList());
    }
}
