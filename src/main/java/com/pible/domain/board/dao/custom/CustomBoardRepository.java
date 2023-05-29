package com.pible.domain.board.dao.custom;

import com.pible.domain.channel.model.BoardContentRes;
import com.pible.domain.channel.model.ContentDto;

import java.util.List;

public interface CustomBoardRepository {
    List<BoardContentRes> selectBoardContents(Long channelId, ContentDto contentDto);
}
