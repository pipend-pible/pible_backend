package com.pible.domain.channel.service;

import com.pible.domain.channel.model.ChannelDto;
import com.pible.domain.channel.model.ChannelRes;

import java.util.List;

public interface ChannelService {
    ChannelRes saveChannel(ChannelDto channelDto);
    boolean deleteChannel(Long channelId);
    List<ChannelRes> getChannels();
}
