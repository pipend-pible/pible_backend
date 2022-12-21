package com.pible.domain.channel.controller;

import com.pible.domain.channel.model.ChannelDto;
import com.pible.domain.channel.model.ChannelRes;
import com.pible.domain.channel.service.ChannelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @PostMapping("/delete/{channel_id}")
    public boolean deleteChannel(@PathVariable(value = "channel_id") @NotNull Long channelId) {
        return channelService.deleteChannel(channelId);
    }

    @GetMapping("/list")
    public List<ChannelRes> getChannels() {
        return channelService.getChannels();
    }
}
