package com.pible.domain.fanart.mapper;

import com.pible.common.entity.FanartEntity;
import com.pible.common.mapper.GenericMapper;
import com.pible.domain.fanart.model.FanartDto;
import com.pible.domain.fanart.model.FanartRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FanartMapper extends GenericMapper<FanartEntity, FanartDto> {
    FanartMapper INSTANCE = Mappers.getMapper(FanartMapper.class);
    @Mapping(source = "id", target = "fanartId")
    @Mapping(source = "userEntity", target = "userNickName", qualifiedByName = "userEntityToUserNickName")
    @Mapping(source = "channelEntity", target = "channelId", qualifiedByName = "channelEntityToId")
    @Mapping(source = "fanartCategoryEntity", target = "fanartCategoryId", qualifiedByName = "fanartCategoryEntityToId")
    FanartRes entityToFanartRes(FanartEntity entity);

    default FanartRes entityToFanartRes(FanartEntity entity, List<String> tagList) {
        FanartRes fanartRes = entityToFanartRes(entity);
        fanartRes.setTagList(tagList);
        return fanartRes;
    }
}
