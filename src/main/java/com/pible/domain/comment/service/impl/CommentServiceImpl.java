package com.pible.domain.comment.service.impl;

import com.pible.domain.board.dao.BoardRepository;
import com.pible.domain.comment.dao.CommentRepository;
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

    @Override
    public CommentRes createComment(CommentDto commentDto) {


        return null;
    }
}
