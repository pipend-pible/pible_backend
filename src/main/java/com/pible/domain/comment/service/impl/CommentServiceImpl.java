package com.pible.domain.comment.service.impl;

import com.pible.common.entity.CommentEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.domain.board.dao.BoardRepository;
import com.pible.domain.comment.dao.CommentRepository;
import com.pible.domain.comment.mapper.CommentMapper;
import com.pible.domain.comment.model.CommentDto;
import com.pible.domain.comment.model.CommentRes;
import com.pible.domain.comment.service.CommentService;
import com.pible.domain.fanart.dao.FanartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final FanartRepository fanartRepository;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;
    @Override
    @Transactional
    public CommentRes createComment(CommentDto commentDto) {
        return commentMapper.entityToCommentRes(
                commentRepository.save(
                        commentMapper.dtoToEntity(commentDto)
                )
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
