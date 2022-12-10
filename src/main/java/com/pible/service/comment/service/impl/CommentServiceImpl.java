package com.pible.service.comment.service.impl;

import com.pible.service.board.dao.BoardRepository;
import com.pible.service.comment.dao.CommentRepository;
import com.pible.service.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
}
