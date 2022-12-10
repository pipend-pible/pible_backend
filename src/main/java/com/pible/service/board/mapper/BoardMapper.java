package com.pible.service.board.mapper;

import com.pible.service.board.model.BoardDto;
import com.pible.common.mapper.GenericMapper;
import com.pible.common.entity.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper extends GenericMapper<BoardEntity, BoardDto> {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);
}
