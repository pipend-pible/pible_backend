package com.pible.domain.board.dao.custom;

import com.pible.domain.channel.model.ContentRes;

import java.util.List;

public interface CustomBoardRepository {
    List<ContentRes> findAllByChannel(Long channelId);
}
