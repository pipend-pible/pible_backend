package com.pible.domain.channel.controller;

import com.pible.domain.board.model.BoardDto;
import com.pible.domain.channel.model.ChannelDto;
import com.pible.domain.channel.model.ChannelRes;
import com.pible.domain.channel.model.ContentRes;
import com.pible.domain.channel.service.ChannelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/channel")
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping("/create")
    public ChannelRes saveChannel(@RequestBody @Valid ChannelDto channelDto) {
        return channelService.saveChannel(channelDto);
    }

    @PostMapping("/delete/{channelId}")
    public boolean deleteChannel(@PathVariable Long channelId) {
        return channelService.deleteChannel(channelId);
    }

    @GetMapping("/{channelId}")
    public ChannelRes getChannel(@PathVariable Long channelId) {
        return channelService.getChannel(channelId);
    }

    @GetMapping("/list")
    public List<ChannelRes> getChannels() {
        return channelService.getChannels();
    }

    @GetMapping("/{channelId}/contents")
    public List<? extends ContentRes> getChannelContents(@PathVariable Long channelId, BoardDto boardDto) {
        return channelService.getChannelContents(channelId, boardDto);
    }
}
