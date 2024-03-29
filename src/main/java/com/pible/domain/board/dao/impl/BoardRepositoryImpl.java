package com.pible.domain.board.dao.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.domain.board.dao.custom.CustomBoardRepository;
import com.pible.domain.channel.model.BoardContentRes;
import com.pible.domain.channel.model.ContentDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.pible.common.entity.QBoardCategoryEntity.boardCategoryEntity;
import static com.pible.common.entity.QBoardEntity.boardEntity;
import static com.pible.common.entity.QBoradTagMappingEntity.boradTagMappingEntity;
import static com.pible.common.entity.QChannelEntity.channelEntity;
import static com.pible.common.entity.QTagEntity.tagEntity;
import static com.pible.common.entity.QUserEntity.userEntity;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements CustomBoardRepository {
    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BoardEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<BoardContentRes> selectBoardContents(Long channelId, ContentDto contentDto) {
        return queryFactory.select(
                Projections.constructor(BoardContentRes.class,
                        channelEntity.id,
                        boardCategoryEntity.categoryName.as("category"),
                        boardEntity.title,
                        userEntity.email,
                        userEntity.nickName,
                        boardEntity.likeCount,
                        boardEntity.hitCount,
                        // 게시글 조회시 태그목록을 flat 하여 가져오기 위한 설정입니다.
                        Expressions.stringTemplate("string_agg({0}, {1})", tagEntity.tag.coalesce(""), ",").as("tagList"),
                        boardEntity.createDate,
                        boardEntity.id
                    )
                )
                .from(boardEntity)
                .join(boardEntity.channelEntity, channelEntity)
                    .on(channelEntity.id.eq(channelId))
                .join(boardEntity.userEntity, userEntity)
                .join(boardEntity.boardCategoryEntity, boardCategoryEntity)
                .leftJoin(boradTagMappingEntity)
                    .on(boradTagMappingEntity.boardEntity.eq(boardEntity))
                .leftJoin(tagEntity)
                    .on(boradTagMappingEntity.tagEntity.eq(tagEntity))
                .where(
                        containsTitle(contentDto.getTitle()),
                        isAnonymous(contentDto.getBoardAnonymous()),
                        eqUserId(contentDto.getUserId()),
                        containsTags(contentDto.getTagList())
                )
                .groupBy(channelEntity.id, boardEntity.id, boardCategoryEntity.categoryName, boardEntity.title, userEntity.id,
                        userEntity.email, userEntity.nickName, boardEntity.likeCount, boardEntity.hitCount)
                .fetch();
    }

    private BooleanExpression containsTitle(String title) {
        if(!StringUtils.hasLength(title)) {
            return null;
        }

        return boardEntity.title.contains(title);
    }

    private BooleanExpression containsTags(List<String> tagList) {
        if(CollectionUtils.isEmpty(tagList)) {
            return null;
        }

        return tagEntity.tag.in(tagList);
    }

    private BooleanExpression isAnonymous(String boardAnonymous) {
        if(!StringUtils.hasLength(boardAnonymous)) {
            return null;
        }

        return boardEntity.anonymousYn.eq(boardAnonymous);
    }

    private BooleanExpression eqUserId(Long userId) {
        if(userId == null) {
            return null;
        }

        return userEntity.id.eq(userId);
    }
}
