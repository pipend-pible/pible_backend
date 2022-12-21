package com.pible.domain.fanart.service.impl;

import com.pible.common.entity.*;
import com.pible.common.exception.CustomException;
import com.pible.domain.category.fanart.dao.FanartCategoryRepository;
import com.pible.domain.channel.dao.ChannelRepository;
import com.pible.domain.fanart.dao.FanartRepository;
import com.pible.domain.fanart.mapper.FanartMapper;
import com.pible.domain.fanart.model.FanartDto;
import com.pible.domain.fanart.model.FanartRes;
import com.pible.domain.fanart.service.FanartService;
import com.pible.domain.mapping.dao.FanartTagMappingRepository;
import com.pible.domain.tag.dao.TagRepository;
import com.pible.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FanartServiceImpl implements FanartService {
    private final FanartRepository fanartRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final FanartCategoryRepository fanartCategoryRepository;
    private final FanartTagMappingRepository fanartTagMappingRepository;
    private final FanartMapper fanartMapper = FanartMapper.INSTANCE;

    @Override
    @Transactional
    public FanartRes saveFanart(FanartDto fanartDto) {
        FanartEntity fanartEntity = fanartMapper.dtoToEntity(fanartDto);

        fanartEntity.setRelation(
                channelRepository.getReferenceById(fanartDto.getChannelId()),
                // TODO 회원가입, 인증 이후
                userRepository.getReferenceById(fanartDto.getUserId()),
                fanartCategoryRepository.getReferenceById(fanartDto.getFanartCategoryId())
        );

        fanartEntity = fanartRepository.save(fanartEntity);

        for(String tag : fanartDto.getTagList()) {
            TagEntity tagEntity = tagRepository.findByTag(tag).orElseGet(
                    () -> tagRepository.save(TagEntity.builder().tag(tag).build())
            );

            fanartTagMappingRepository.save(FanartTagMappingEntity.builder()
                    .fanartEntity(fanartEntity)
                    .tagEntity(tagEntity)
                    .build());
        }

        return fanartMapper.entityToFanartRes(fanartEntity);
    }

    @Override
    public FanartRes getFanart(Long fanartId) {
        return fanartMapper.entityToFanartRes(
                fanartRepository.findById(fanartId).orElseThrow(() -> new CustomException(""))
        );
    }
}
