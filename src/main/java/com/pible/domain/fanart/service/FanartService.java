package com.pible.domain.fanart.service;

import com.pible.domain.fanart.model.FanartDto;
import com.pible.domain.fanart.model.FanartRes;

public interface FanartService {
    FanartRes saveFanart(FanartDto fanartDto);
    FanartRes getFanart(Long fanartId);
}
