package com.pible.channel.service;

import com.pible.channel.model.ChannelDto;
import com.pible.channel.model.ChannelRes;

import java.util.List;

public interface ChannelService {
    ChannelRes saveChannel(ChannelDto channelDto);
    boolean deleteChannel(Long channelId);
    List<ChannelRes> getChannels();
}
