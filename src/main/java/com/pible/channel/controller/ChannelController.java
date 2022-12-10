package com.pible.channel.controller;

import com.pible.channel.model.ChannelDto;
import com.pible.channel.model.ChannelRes;
import com.pible.channel.service.ChannelService;
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

    @PostMapping("/delete/{channelId}")
    public boolean deleteChannel(@PathVariable @NotNull Long channelId) {
        return channelService.deleteChannel(channelId);
    }

    @GetMapping("/list")
    public List<ChannelRes> getChannels() {
        return channelService.getChannels();
    }
}
