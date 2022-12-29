package com.pible.domain.fanart.service;

import com.pible.domain.channel.model.ContentDto;
import com.pible.domain.channel.model.FanartContentRes;
import com.pible.domain.fanart.model.FanartDto;
import com.pible.domain.fanart.model.FanartRes;

import java.util.List;

public interface FanartService {
    FanartRes saveFanart(FanartDto fanartDto);
    FanartRes getFanart(Long fanartId);
    List<FanartContentRes> getFanartList(ContentDto contentDto);
}
