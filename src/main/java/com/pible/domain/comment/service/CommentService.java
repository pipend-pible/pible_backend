package com.pible.domain.comment.service;

import com.pible.domain.comment.model.CommentDto;
import com.pible.domain.comment.model.CommentRes;

import java.util.List;

public interface CommentService {
    List<CommentRes> getBoardCommentList(Long boardId);
    List<CommentRes> getFanartCommentList(Long fanartId);
    CommentRes createComment(CommentDto commentDto);
    boolean likeComment(Long commentId);
    boolean deleteComment(Long commentId);
    boolean modifyComment(CommentDto commentDto, Long commentId);
    boolean claimComment(Long commentId);
}
