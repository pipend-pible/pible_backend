package com.pible.domain.channel.service.impl;

import com.pible.common.entity.ChannelEntity;
import com.pible.common.exception.CustomException;
import com.pible.domain.board.dao.BoardRepository;
import com.pible.domain.channel.dao.ChannelRepository;
import com.pible.domain.channel.mapper.ChannelMapper;
import com.pible.domain.channel.model.*;
import com.pible.domain.channel.service.ChannelService;
import com.pible.domain.fanart.dao.FanartRepository;
import com.pible.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;
    private final BoardRepository boardRepository;
    private final FanartRepository fanartRepository;
    private final UserRepository userRepository;
    private final ChannelMapper channelMapper = ChannelMapper.INSTANCE;

    @Override
    @Transactional
    public ChannelRes saveChannel(ChannelDto channelDto) {
        return channelMapper.entityToChannelRes(
                channelRepository.save(
                        channelMapper.dtoToEntity(channelDto)
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
    public ChannelRes getChannel(Long channelId) {
        return channelMapper.entityToChannelRes(
                channelRepository.findById(channelId).orElseThrow(() -> new CustomException(""))
        );
    }

    @Override
    public List<ChannelRes> getChannels() {
        return channelRepository.findAll().stream().map(channelMapper::entityToChannelRes).collect(Collectors.toList());
    }

    @Override
    public List<? extends ContentRes> getChannelContents(Long channelId, ContentDto contentDto) {
        ChannelEntity channelEntity = channelRepository.findById(channelId).orElseThrow(() -> new CustomException(""));

        List<BoardContentRes> boardContentResList = boardRepository.selectBoardContents(
                channelEntity.getId(),
                contentDto
        );

        List<FanartContentRes> fanartContentResList = fanartRepository.selectFanartContents(
                channelEntity.getId(),
                contentDto
        );

        return Stream.concat(boardContentResList.stream(), fanartContentResList.stream())
                .sorted(Comparator.comparing(ContentRes::getCreateDate))
                .collect(Collectors.toList());
    }
}
