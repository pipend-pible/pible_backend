package com.pible.domain.fanart.dao.custom;

import com.pible.domain.channel.model.ContentDto;
import com.pible.domain.channel.model.FanartContentRes;

import java.util.List;

public interface CustomFanartRepository {
    List<FanartContentRes> selectFanartContents(Long channelId, ContentDto contentDto);
}
