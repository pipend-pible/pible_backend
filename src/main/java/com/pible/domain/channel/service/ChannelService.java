package com.pible.domain.channel.service;

import com.pible.domain.board.model.BoardDto;
import com.pible.domain.channel.model.BoardContentRes;
import com.pible.domain.channel.model.ChannelDto;
import com.pible.domain.channel.model.ChannelRes;

import java.util.List;

public interface ChannelService {
    ChannelRes saveChannel(ChannelDto channelDto);
    boolean deleteChannel(Long channelId);
    ChannelRes getChannel(Long channelId);
    List<ChannelRes> getChannels();
    List<BoardContentRes> getChannelContents(Long channelId, BoardDto boardDto);
}
