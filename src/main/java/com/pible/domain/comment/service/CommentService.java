package com.pible.domain.comment.service;

import com.pible.domain.comment.model.CommentDto;
import com.pible.domain.comment.model.CommentRes;

public interface CommentService {
    CommentRes createComment(CommentDto commentDto);
    boolean likeComment(Long commentId);
    boolean deleteComment(Long commentId);
    boolean modifyComment(CommentDto commentDto, Long commentId);
    boolean claimComment(Long commentId);
}
