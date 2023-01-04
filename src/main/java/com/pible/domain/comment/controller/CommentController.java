package com.pible.domain.comment.controller;

import com.pible.domain.comment.model.CommentDto;
import com.pible.domain.comment.model.CommentRes;
import com.pible.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public CommentRes createComment(@RequestBody @Valid CommentDto commentDto) {
        return commentService.createComment(commentDto);
    }

    @PostMapping("/like/{commentId}")
    public CommentRes likeComment(@PathVariable Long commentId) {
        return commentService.likeComment(commentId);
    }

    @DeleteMapping("/{commentId}")
    public boolean deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @PutMapping("/{commentId}")
    public boolean modifyComment(@RequestBody @Valid CommentDto commentDto) {
        return commentService.modifyComment(commentDto);
    }

    // TODO 댓글 신고기능 합의
}
