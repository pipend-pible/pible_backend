package com.pible.channel.service.impl;

import com.pible.channel.dao.ChannelRepository;
import com.pible.channel.mapper.ChannelMapper;
import com.pible.channel.model.ChannelDto;
import com.pible.channel.model.ChannelRes;
import com.pible.channel.service.ChannelService;
import com.pible.common.exception.CustomException;
import com.pible.model.entity.ChannelEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;
    private final ChannelMapper channelMapper = ChannelMapper.INSTANCE;

    @Override
    @Transactional
    public ChannelRes saveChannel(ChannelDto channelDto) {
        return channelMapper.entityToChannelRes(
                channelRepository.save(
                        channelMapper.channelDtoToEntity(channelDto)
                )
        );
    }

    @Override
    @Transactional
    public boolean deleteChannel(Long channelId) {
        ChannelEntity channelEntity = channelRepository.findById(channelId).orElseThrow(() -> new CustomException(""));
        channelEntity.delete();
        return true;
    }

    @Override
    public List<ChannelRes> getChannels() {
        return channelRepository.findAll().stream().map(channelMapper::entityToChannelRes).collect(Collectors.toList());
    }
}
