package com.pible.domain.user.mapper;

import com.pible.common.entity.UserEntity;
import com.pible.common.mapper.GenericMapper;
import com.pible.domain.user.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends GenericMapper<UserEntity, UserDto> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
  
}
