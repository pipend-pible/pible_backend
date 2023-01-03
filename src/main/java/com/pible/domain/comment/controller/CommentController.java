package com.pible.domain.comment.controller;

import com.pible.domain.comment.model.CommentDto;
import com.pible.domain.comment.model.CommentRes;
import com.pible.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public CommentRes createComment(CommentDto commentDto) {
        return commentService.createComment(commentDto);
    }
}
