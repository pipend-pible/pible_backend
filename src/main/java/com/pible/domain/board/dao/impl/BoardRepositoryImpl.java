package com.pible.domain.board.dao.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.domain.board.dao.custom.CustomBoardRepository;
import com.pible.domain.board.model.BoardDto;
import com.pible.domain.channel.model.BoardContentRes;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

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
    public List<BoardContentRes> selectBoardContents(Long channelId, BoardDto boardDto) {
        return queryFactory.select(
                Projections.constructor(BoardContentRes.class,
                        channelEntity.id,
                        channelEntity.category,
                        boardEntity.title,
                        userEntity.id,
                        userEntity.email,
                        userEntity.nickName,
                        boardEntity.likeCount,
                        boardEntity.hitCount,
                        Expressions.stringTemplate("string_agg({0}, {1})", tagEntity.tag, ",").as("tagList"),
                        boardEntity.id
                    )
                )
                .from(boardEntity)
                .join(boardEntity.channelEntity, channelEntity)
                    .on(channelEntity.id.eq(channelId))
                .join(boardEntity.userEntity, userEntity)
                .leftJoin(boradTagMappingEntity)
                    .on(boradTagMappingEntity.boardEntity.eq(boardEntity))
                .leftJoin(tagEntity)
                    .on(boradTagMappingEntity.tagEntity.eq(tagEntity))
                .where(
                        containsTitle(boardDto.getTitle()),
                        isAnonymous(boardDto.getBoardAnonymous()),
                        eqUserId(boardDto.getUserId()),
                        containsTags(boardDto.getTagList())
                )
                .groupBy(channelEntity.id, boardEntity.id, channelEntity.category, boardEntity.title, userEntity.id,
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
            boardAnonymous = "N";
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
