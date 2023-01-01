package com.pible.common.mapper;

import com.pible.common.entity.BoardCategoryEntity;
import com.pible.common.entity.ChannelEntity;
import com.pible.common.entity.FanartCategoryEntity;
import com.pible.common.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface GenericMapper<E, D> {

    D entityToDto(E entity);
    E dtoToEntity(D dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(D dto, @MappingTarget E entity);

    @Named("userEntityToId")
    default Long userEntityToId(UserEntity userEntity) {
        return userEntity.getId();
    }

    @Named("channelEntityToId")
    default Long channelEntityToId(ChannelEntity channelEntity) {
        return channelEntity.getId();
    }

    @Named("boardCategoryEntityToId")
    default Long boardCategoryEntityToId(BoardCategoryEntity boardCategoryEntity) {
        return boardCategoryEntity.getId();
    }

    @Named("fanartCategoryEntityToId")
    default Long fanartCategoryEntityToId(FanartCategoryEntity fanartCategoryEntity) {
        return fanartCategoryEntity.getId();
    }
}
