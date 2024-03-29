package com.pible.domain.fanart.service.impl;

import com.pible.common.entity.FanartEntity;
import com.pible.common.entity.FanartTagMappingEntity;
import com.pible.common.entity.ImageEntity;
import com.pible.common.entity.TagEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.config.sercurity.utils.SecurityUtils;
import com.pible.domain.category.fanart.dao.FanartCategoryRepository;
import com.pible.domain.channel.dao.ChannelRepository;
import com.pible.domain.channel.model.ContentDto;
import com.pible.domain.channel.model.FanartContentRes;
import com.pible.domain.fanart.dao.FanartRepository;
import com.pible.domain.fanart.mapper.FanartMapper;
import com.pible.domain.fanart.model.FanartDto;
import com.pible.domain.fanart.model.FanartRes;
import com.pible.domain.fanart.service.FanartService;
import com.pible.domain.image.dao.ImageRepository;
import com.pible.domain.image.service.ImageService;
import com.pible.domain.mapping.dao.FanartTagMappingRepository;
import com.pible.domain.tag.dao.TagRepository;
import com.pible.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FanartServiceImpl implements FanartService {
    private final FanartRepository fanartRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final FanartCategoryRepository fanartCategoryRepository;
    private final FanartTagMappingRepository fanartTagMappingRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final FanartMapper fanartMapper = FanartMapper.INSTANCE;

    @Override
    @Transactional
    // 팬아트도 게시글과 마찬가지로 다른 테이블과의 연관관계, 태그, 이미지 관련 작업들이 추가되었습니다.
    public FanartRes saveFanart(List<MultipartFile> multipartFileList, MultipartFile multipartFile, FanartDto fanartDto) {
        FanartEntity fanartEntity = fanartMapper.dtoToEntity(fanartDto);

        fanartEntity.setRelation(
                channelRepository.getReferenceById(fanartDto.getChannelId()),
                userRepository.findById(SecurityUtils.getUserId()).orElseThrow(),
                fanartCategoryRepository.getReferenceById(fanartDto.getFanartCategoryId())
        );

        fanartEntity = fanartRepository.save(fanartEntity);

        // 팬아트는 게시글과 다르게 썸네일 이라는 별도의 이미지파일을 받아 사용합니다.
        imageService.uploadImageFiles(multipartFileList, null, fanartEntity);
        imageService.uploadThumbnail(multipartFile, fanartEntity);

        if(CollectionUtils.isEmpty(fanartDto.getTagList())) {
            return fanartMapper.entityToFanartRes(fanartEntity);
        }

        for(String tag : fanartDto.getTagList()) {
            TagEntity tagEntity = tagRepository.findByTag(tag).orElseGet(
                    () -> tagRepository.save(TagEntity.builder().tag(tag).build())
            );

            fanartTagMappingRepository.save(FanartTagMappingEntity.builder()
                    .fanartEntity(fanartEntity)
                    .tagEntity(tagEntity)
                    .build());
        }

        return fanartMapper.entityToFanartRes(fanartEntity, fanartDto.getTagList());
    }

    @Override
    @Transactional
    public FanartRes getFanart(Long fanartId) {
        FanartEntity fanartEntity = fanartRepository.findById(fanartId)
                .orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        FanartRes fanartRes = fanartMapper.entityToFanartRes(fanartEntity);

        fanartRes.setImageUrlList(
                imageRepository.findAllByFanartEntity(fanartEntity)
                        .stream()
                        .map(ImageEntity::getImageUrl)
                        .collect(Collectors.toList())
        );

        fanartEntity.plusHitCount();

        return fanartRes;
    }

    @Override
    public List<FanartContentRes> getFanartList(ContentDto contentDto) {
        return fanartRepository.selectFanartContents(contentDto.getChannelId(), contentDto);
    }
}
