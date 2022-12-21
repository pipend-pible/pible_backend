package com.pible.domain.fanart.controller;

import com.pible.domain.fanart.model.FanartDto;
import com.pible.domain.fanart.model.FanartRes;
import com.pible.domain.fanart.service.FanartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/fanart")
public class FanartController {
    private final FanartService fanartService;

    @PostMapping("/create")
    public FanartRes saveFanart(@RequestBody @Valid FanartDto fanartDto) {
        return fanartService.saveFanart(fanartDto);
    }

    @GetMapping("/{fanart_id}")
    public FanartRes getFanart(@PathVariable(value = "fanart_id") @Valid Long fanartId) {
        return fanartService.getFanart(fanartId);
    }

}
