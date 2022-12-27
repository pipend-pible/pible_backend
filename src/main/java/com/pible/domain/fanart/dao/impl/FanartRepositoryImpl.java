package com.pible.domain.fanart.dao.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.domain.channel.model.FanartContentRes;
import com.pible.domain.fanart.dao.custom.CustomFanartRepository;
import com.pible.domain.fanart.model.FanartDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import static com.pible.common.querydsl.BooleanExpressionUtil.*;

import java.util.List;

import static com.pible.common.entity.QBoardEntity.boardEntity;
import static com.pible.common.entity.QBoradTagMappingEntity.boradTagMappingEntity;
import static com.pible.common.entity.QChannelEntity.channelEntity;
import static com.pible.common.entity.QTagEntity.tagEntity;
import static com.pible.common.entity.QUserEntity.userEntity;

public class FanartRepositoryImpl extends QuerydslRepositorySupport implements CustomFanartRepository {
    private final JPAQueryFactory queryFactory;

    public FanartRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BoardEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<FanartContentRes> selectFanartContents(Long channelId, FanartDto fanartDto) {
        return queryFactory.select(
                Projections.constructor(FanartContentRes.class,
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
                        contains(boardEntity.title, fanartDto.getTitle()),
                        eq(userEntity.id, fanartDto.getUserId()),
                        containsList(tagEntity.tag, fanartDto.getTagList())
                )
                .groupBy(channelEntity.id, boardEntity.id, channelEntity.category, boardEntity.title, userEntity.id,
                        userEntity.email, userEntity.nickName, boardEntity.likeCount, boardEntity.hitCount)
                .fetch();
    }

}
