package com.pible.domain.comment.mapper;

import com.pible.common.entity.CommentEntity;
import com.pible.common.entity.UserEntity;
import com.pible.common.mapper.GenericMapper;
import com.pible.domain.comment.model.CommentDto;
import com.pible.domain.comment.model.CommentRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper extends GenericMapper<CommentEntity, CommentDto> {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "commentId")
    @Mapping(source = "userEntity", target = "userId", qualifiedByName = "userEntityToId")
    @Mapping(source = "boardEntity", target = "boardId", qualifiedByName = "boardEntityToId")
    @Mapping(source = "fanartEntity", target = "fanartId", qualifiedByName = "fanartEntityToId")
    @Mapping(source = "userEntity", target = "nickName", qualifiedByName = "userEntityToNickName")
    CommentRes entityToCommentRes(CommentEntity entity);

    @Named("userEntityToNickName")
    default String userEntityToNickName(UserEntity userEntity) {
        return userEntity.getNickName();
    }
}
