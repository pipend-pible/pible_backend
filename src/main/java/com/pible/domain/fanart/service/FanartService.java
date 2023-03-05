package com.pible.domain.fanart.service;

import com.pible.domain.channel.model.ContentDto;
import com.pible.domain.channel.model.FanartContentRes;
import com.pible.domain.fanart.model.FanartDto;
import com.pible.domain.fanart.model.FanartRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FanartService {
    FanartRes saveFanart(List<MultipartFile> multipartFileList, MultipartFile multipartFile, FanartDto fanartDto);
    FanartRes getFanart(Long fanartId);
    List<FanartContentRes> getFanartList(ContentDto contentDto);
}
