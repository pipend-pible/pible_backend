package com.pible.domain.comment.controller;

import com.pible.domain.comment.model.CommentDto;
import com.pible.domain.comment.model.CommentRes;
import com.pible.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/board/{boardId}")
    public List<CommentRes> getBoardCommentList(@PathVariable Long boardId) {
        return commentService.getBoardCommentList(boardId);
    }

    @GetMapping("/fanart/{fanartId}")
    public List<CommentRes> getFanartCommentList(@PathVariable Long fanartId) {
        return commentService.getFanartCommentList(fanartId);
    }

    @PostMapping("")
    public CommentRes createComment(@RequestBody @Valid CommentDto commentDto) {
        return commentService.createComment(commentDto);
    }

    @PostMapping("/like/{commentId}")
    public boolean likeComment(@PathVariable Long commentId) {
        return commentService.likeComment(commentId);
    }

    @DeleteMapping("/{commentId}")
    public boolean deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @PutMapping("/{commentId}")
    public boolean modifyComment(@RequestBody @Valid CommentDto commentDto, @PathVariable Long commentId) {
        return commentService.modifyComment(commentDto, commentId);
    }

    @PutMapping("/claim/{commentId}")
    public boolean claimComment(@PathVariable Long commentId) {
        return commentService.claimComment(commentId);
    }

    // TODO 댓글 신고기능 합의
}
