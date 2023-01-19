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

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final FanartRepository fanartRepository;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;
    @Override
    public CommentRes createComment(CommentDto commentDto) {
        return commentMapper.entityToCommentRes(
                commentRepository.save(
                        commentMapper.dtoToEntity(commentDto)
                )
        );
    }

    @Override
    public CommentRes likeComment(Long commentId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        commentEntity.like();

        return commentMapper.entityToCommentRes(commentEntity);
    }

    @Override
    public boolean deleteComment(Long commentId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        commentEntity.delete();

        return true;
    }

    @Override
    public boolean modifyComment(CommentDto commentDto) {
        CommentEntity commentEntity = commentRepository.findById(commentDto.getBoardId()).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        commentMapper.updateFromDto(commentDto, commentEntity);

        return true;
    }
}
