package com.pible.domain.channel.service.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.ChannelEntity;
import com.pible.common.entity.FanartEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.domain.board.dao.BoardRepository;
import com.pible.domain.channel.dao.ChannelRepository;
import com.pible.domain.channel.mapper.ChannelMapper;
import com.pible.domain.channel.model.*;
import com.pible.domain.channel.service.ChannelService;
import com.pible.domain.fanart.dao.FanartRepository;
import com.pible.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        ChannelEntity channelEntity = channelRepository.findById(channelId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        channelEntity.delete();
        return true;
    }

    @Override
    public ChannelRes getChannel(Long channelId) {
        return channelMapper.entityToChannelRes(
                channelRepository.findById(channelId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA))
        );
    }

    @Override
    public List<ChannelRes> getChannels() {
        return channelRepository.findAll().stream().map(channelMapper::entityToChannelRes).collect(Collectors.toList());
    }

    // 게시글과 팬아트는 동일한 속성의 값이 많기 때문에 ContentRes 라는 공통의 응답 포맷을 사용합니다.
    @Override
    public List<? extends ContentRes> getChannelContents(Long channelId, ContentDto contentDto) {
        ChannelEntity channelEntity = channelRepository.findById(channelId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));

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

    // 채널별 게시글 / 팬아트 별 총 조회수를 계산하기 위한 작업입니다.
    // 월요일 오전 6시에 동작합니다.
    @Scheduled(cron = "0 0 6 * * 1")
    @Transactional
    public void calculateTotalCount() {
        Map<Long, List<BoardEntity>> boardMapByChannel = boardRepository.findAll().stream().collect(Collectors.groupingBy((boardEntity -> boardEntity.getChannelEntity().getId())));
        Map<Long, List<FanartEntity>> fanartMapByChannel = fanartRepository.findAll().stream().collect(Collectors.groupingBy((fanartEntity -> fanartEntity.getChannelEntity().getId())));

        channelRepository.findAll().forEach(channelEntity -> {
            channelEntity.setBoardTotalHitCount(
                    boardMapByChannel.get(channelEntity.getId())
                            .stream()
                            .mapToInt(BoardEntity::getHitCount)
                            .sum()
            );
            channelEntity.setFanartTotalHitCount(
                    fanartMapByChannel.get(channelEntity.getId())
                            .stream()
                            .mapToInt(FanartEntity::getHitCount)
                            .sum()
            );
        });
    }
}
