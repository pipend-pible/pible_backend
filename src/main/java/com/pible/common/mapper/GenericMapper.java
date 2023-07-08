package com.pible.common.mapper;

import com.pible.common.entity.*;
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

    @Named("userEntityToUserNickName")
    default String userEntityToUserNickName(UserEntity userEntity) {
        return userEntity.getNickName();
    }

    @Named("channelEntityToId")
    default Long channelEntityToId(ChannelEntity channelEntity) {
        return channelEntity.getId();
    }

    @Named("fanartEntityToId")
    default Long fanartEntityToId(FanartEntity fanartEntity) {
        if(fanartEntity == null) {
            return null;
        }

        return fanartEntity.getId();
    }

    @Named("boardEntityToId")
    default Long boardEntityToId(BoardEntity boardEntity) {
        if(boardEntity == null) {
            return null;
        }

        return boardEntity.getId();
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
