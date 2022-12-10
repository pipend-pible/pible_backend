package com.pible.service.channel.service;

import com.pible.service.channel.model.ChannelDto;
import com.pible.service.channel.model.ChannelRes;

import java.util.List;

public interface ChannelService {
    ChannelRes saveChannel(ChannelDto channelDto);
    boolean deleteChannel(Long channelId);
    List<ChannelRes> getChannels();
}
