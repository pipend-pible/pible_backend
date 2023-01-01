package com.pible.domain.board.mapper;

import com.pible.common.entity.BoardEntity;
import com.pible.common.mapper.GenericMapper;
import com.pible.domain.board.model.BoardDto;
import com.pible.domain.board.model.BoardRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper extends GenericMapper<BoardEntity, BoardDto> {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    @Mapping(source = "id", target = "boardId")
    @Mapping(source = "userEntity", target = "userId", qualifiedByName = "userEntityToId")
    @Mapping(source = "channelEntity", target = "channelId", qualifiedByName = "channelEntityToId")
    @Mapping(source = "boardCategoryEntity", target = "boardCategoryId", qualifiedByName = "boardCategoryEntityToId")
    BoardRes entityToBoardRes(BoardEntity entity);

}
