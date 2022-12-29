package com.pible.domain.channel.service;

import com.pible.domain.channel.model.ChannelDto;
import com.pible.domain.channel.model.ChannelRes;
import com.pible.domain.channel.model.ContentDto;
import com.pible.domain.channel.model.ContentRes;

import java.util.List;

public interface ChannelService {
    ChannelRes saveChannel(ChannelDto channelDto);
    boolean deleteChannel(Long channelId);
    ChannelRes getChannel(Long channelId);
    List<ChannelRes> getChannels();
    List<? extends ContentRes> getChannelContents(Long channelId, ContentDto contentDto);
}
