package com.pible.service.channel.mapper;

import com.pible.service.channel.model.ChannelDto;
import com.pible.service.channel.model.ChannelRes;
import com.pible.common.mapper.GenericMapper;
import com.pible.common.entity.ChannelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChannelMapper extends GenericMapper<ChannelEntity, ChannelDto> {
    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    @Mapping(source = "id", target = "channelId")
    ChannelRes entityToChannelRes(ChannelEntity entity);
}
