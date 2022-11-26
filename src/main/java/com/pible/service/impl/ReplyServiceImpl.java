package com.pible.service.impl;

import com.pible.common.exception.CustomException;
import com.pible.dao.BoardRepository;
import com.pible.dao.ReplyRepository;
import com.pible.model.dto.ReplyDto;
import com.pible.model.entity.CommentEntity;
import com.pible.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    @Override
    public ReplyDto saveReply(ReplyDto replyDto) {
        replyRepository.save(CommentEntity.builder()
                .content(replyDto.getComment())
                .boardEntity(boardRepository.findById(replyDto.getBoardId())
                        .orElseThrow(() -> new CustomException("")))
                .build());
        return replyDto;
    }
}
