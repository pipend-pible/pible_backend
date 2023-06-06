package com.pible.domain.comment.service.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.CommentEntity;
import com.pible.common.entity.FanartEntity;
import com.pible.common.entity.UserEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.domain.board.dao.BoardRepository;
import com.pible.domain.comment.dao.CommentRepository;
import com.pible.domain.comment.mapper.CommentMapper;
import com.pible.domain.comment.model.CommentDto;
import com.pible.domain.comment.model.CommentRes;
import com.pible.domain.comment.service.CommentService;
import com.pible.domain.fanart.dao.FanartRepository;
import com.pible.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final FanartRepository fanartRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;

    @Override
    public List<CommentRes> getBoardCommentList(Long boardId) {
        return commentRepository.findAllByBoardEntity(
                boardRepository.findById(boardId)
                        .orElseThrow(() -> new BusinessException(ResponseCode.FAIL))
                )
                .stream()
                .map(commentMapper::entityToCommentRes)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentRes> getFanartCommentList(Long fanartId) {
        return commentRepository.findAllByFanartEntity(
                        fanartRepository.findById(fanartId)
                                .orElseThrow(() -> new BusinessException(ResponseCode.FAIL))
                )
                .stream()
                .map(commentMapper::entityToCommentRes)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentRes createComment(CommentDto commentDto) {
        UserEntity userEntity = userRepository.findById(commentDto.getUserId()).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        BoardEntity boardEntity = null;
        FanartEntity fanartEntity = null;

        if(commentDto.getFanartId() != null && commentDto.getBoardId() != null) {
            throw new BusinessException(ResponseCode.FAIL);
        } else if (commentDto.getBoardId() != null) {
            boardEntity = boardRepository.findById(commentDto.getBoardId())
                    .orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        } else if (commentDto.getFanartId() != null) {
            fanartEntity = fanartRepository.findById(commentDto.getFanartId())
                    .orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        } else {
            throw new BusinessException(ResponseCode.FAIL);
        }

        CommentEntity commentEntity = commentMapper.dtoToEntity(commentDto);
        commentEntity.setEntityRelation(boardEntity, fanartEntity, userEntity);

        return commentMapper.entityToCommentRes(
                commentRepository.save(commentEntity)
        );
    }

    @Override
    @Transactional
    public boolean likeComment(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA)).like();

        return true;
    }

    @Override
    @Transactional
    public boolean deleteComment(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA)).delete();

        return true;
    }

    @Override
    @Transactional
    public boolean modifyComment(CommentDto commentDto, Long commentId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        commentMapper.updateFromDto(commentDto, commentEntity);

        return true;
    }

    @Override
    @Transactional
    public boolean claimComment(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA)).claim();
        return true;
    }
}
