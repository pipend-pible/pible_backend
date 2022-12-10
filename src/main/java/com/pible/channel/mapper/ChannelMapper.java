package com.pible.channel.mapper;

import com.pible.channel.model.ChannelDto;
import com.pible.channel.model.ChannelRes;
import com.pible.model.entity.ChannelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChannelMapper {
    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    ChannelEntity channelDtoToEntity(ChannelDto channelDto);
    @Mapping(source = "id", target = "channelId")
    ChannelRes entityToChannelRes(ChannelEntity entity);
}
