package com.pible.domain.board.dao.custom;

import com.pible.domain.board.model.BoardDto;
import com.pible.domain.channel.model.BoardContentRes;

import java.util.List;

public interface CustomBoardRepository {
    List<BoardContentRes> selectBoardContents(Long channelId, BoardDto boardDto);
}
