package com.pible.domain.fanart.dao.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.FanartEntity;
import com.pible.domain.channel.model.ContentDto;
import com.pible.domain.channel.model.FanartContentRes;
import com.pible.domain.fanart.dao.custom.CustomFanartRepository;
import com.pible.domain.fanart.model.FanartDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.pible.common.entity.QFanartEntity.fanartEntity;
import static com.pible.common.entity.QFanartTagMappingEntity.fanartTagMappingEntity;
import static com.pible.common.entity.QChannelEntity.channelEntity;
import static com.pible.common.entity.QTagEntity.tagEntity;
import static com.pible.common.entity.QUserEntity.userEntity;
import static com.pible.common.querydsl.BooleanExpressionUtil.*;

public class FanartRepositoryImpl extends QuerydslRepositorySupport implements CustomFanartRepository {
    private final JPAQueryFactory queryFactory;

    public FanartRepositoryImpl(JPAQueryFactory queryFactory) {
        super(FanartEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<FanartContentRes> selectFanartContents(Long channelId, ContentDto contentDto) {
        return queryFactory.select(
                Projections.constructor(FanartContentRes.class,
                        channelEntity.id,
                        channelEntity.category,
                        fanartEntity.title,
                        userEntity.id,
                        userEntity.email,
                        userEntity.nickName,
                        fanartEntity.likeCount,
                        fanartEntity.hitCount,
                        Expressions.stringTemplate("string_agg({0}, {1})", tagEntity.tag.coalesce(""), ",").as("tagList"),
                        fanartEntity.createDate,
                        fanartEntity.id
                    )
                )
                .from(fanartEntity)
                .join(fanartEntity.channelEntity, channelEntity)
                .join(fanartEntity.userEntity, userEntity)
                .leftJoin(fanartTagMappingEntity)
                    .on(fanartTagMappingEntity.fanartEntity.eq(fanartEntity))
                .leftJoin(tagEntity)
                    .on(fanartTagMappingEntity.tagEntity.eq(tagEntity))
                .where(
                        contains(fanartEntity.title, contentDto.getTitle()),
                        eq(userEntity.id, contentDto.getUserId()),
                        containsList(tagEntity.tag, contentDto.getTagList()),
                        eq(channelEntity.id, channelId)
                )
                .groupBy(channelEntity.id, fanartEntity.id, channelEntity.category, fanartEntity.title, userEntity.id,
                        userEntity.email, userEntity.nickName, fanartEntity.likeCount, fanartEntity.hitCount)
                .fetch();
    }

}
