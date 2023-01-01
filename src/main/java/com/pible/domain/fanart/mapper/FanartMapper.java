package com.pible.domain.fanart.mapper;

import com.pible.common.entity.FanartEntity;
import com.pible.common.mapper.GenericMapper;
import com.pible.domain.fanart.model.FanartDto;
import com.pible.domain.fanart.model.FanartRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FanartMapper extends GenericMapper<FanartEntity, FanartDto> {
    FanartMapper INSTANCE = Mappers.getMapper(FanartMapper.class);
    @Mapping(source = "id", target = "fanartId")
    @Mapping(source = "userEntity", target = "userId", qualifiedByName = "userEntityToId")
    @Mapping(source = "channelEntity", target = "channelId", qualifiedByName = "channelEntityToId")
    @Mapping(source = "boardCategoryEntity", target = "boardCategoryId", qualifiedByName = "boardCategoryEntityToId")
    FanartRes entityToFanartRes(FanartEntity entity);

}
