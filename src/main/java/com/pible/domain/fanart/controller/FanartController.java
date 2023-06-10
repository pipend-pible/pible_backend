package com.pible.domain.fanart.controller;

import com.pible.common.utils.ObjectMapperUtils;
import com.pible.domain.channel.model.ContentDto;
import com.pible.domain.channel.model.FanartContentRes;
import com.pible.domain.fanart.model.FanartDto;
import com.pible.domain.fanart.model.FanartRes;
import com.pible.domain.fanart.service.FanartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/fanart")
public class FanartController {
    private final FanartService fanartService;

    @PostMapping("/create")
    public FanartRes saveFanart(@RequestParam("fanartDto") String fanart,
                                @RequestParam("images") List<MultipartFile> multipartFileList,
                                @RequestParam("thumbnail") MultipartFile multipartFile) {
        return fanartService.saveFanart(multipartFileList, multipartFile, ObjectMapperUtils.readValue(fanart, FanartDto.class));
    }

    @GetMapping("/{fanartId}")
    public FanartRes getFanart(@PathVariable Long fanartId) {
        return fanartService.getFanart(fanartId);
    }

    @GetMapping("/list")
    public List<FanartContentRes> getFanartList(ContentDto contentDto) {
        return fanartService.getFanartList(contentDto);
    }
}
