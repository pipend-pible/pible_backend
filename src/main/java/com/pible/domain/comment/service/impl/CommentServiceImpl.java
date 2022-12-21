package com.pible.domain.comment.service.impl;

import com.pible.domain.board.dao.BoardRepository;
import com.pible.domain.comment.dao.CommentRepository;
import com.pible.domain.comment.service.CommentService;
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
