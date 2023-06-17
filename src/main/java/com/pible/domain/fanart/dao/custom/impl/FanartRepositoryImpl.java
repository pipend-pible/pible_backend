package com.pible.domain.fanart.dao.custom.impl;

import com.pible.common.Constants;
import com.pible.common.entity.FanartEntity;
import com.pible.domain.channel.model.ContentDto;
import com.pible.domain.channel.model.FanartContentRes;
import com.pible.domain.fanart.dao.custom.CustomFanartRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.pible.common.entity.QChannelEntity.channelEntity;
import static com.pible.common.entity.QFanartCategoryEntity.fanartCategoryEntity;
import static com.pible.common.entity.QFanartEntity.fanartEntity;
import static com.pible.common.entity.QFanartTagMappingEntity.fanartTagMappingEntity;
import static com.pible.common.entity.QImageEntity.imageEntity;
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
                        fanartCategoryEntity.categoryName.as("category"),
                        fanartEntity.title,
                        userEntity.id,
                        userEntity.email,
                        userEntity.nickName,
                        fanartEntity.likeCount,
                        fanartEntity.hitCount,
                        Expressions.stringTemplate("string_agg({0}, {1})", tagEntity.tag.coalesce(""), ",").as("tagList"),
                        fanartEntity.createDate,
                        fanartEntity.id,
                        imageEntity.imageUrl.as("fanartThumbnailImageUrl")
                    )
                )
                .from(fanartEntity)
                .join(fanartEntity.channelEntity, channelEntity)
                .join(fanartEntity.userEntity, userEntity)
                .join(fanartEntity.fanartCategoryEntity, fanartCategoryEntity)
                .leftJoin(fanartTagMappingEntity)
                    .on(fanartTagMappingEntity.fanartEntity.eq(fanartEntity))
                .leftJoin(tagEntity)
                    .on(fanartTagMappingEntity.tagEntity.eq(tagEntity))
                .join(imageEntity)
                    .on(fanartEntity.id.eq(imageEntity.fanartEntity.id))
                .where(
                        contains(fanartEntity.title, contentDto.getTitle()),
                        eq(userEntity.id, contentDto.getUserId()),
                        containsList(tagEntity.tag, contentDto.getTagList()),
                        eq(channelEntity.id, channelId),
                        eq(imageEntity.thumbnailYn, Constants.YES)
                )
                .groupBy(channelEntity.id, fanartEntity.id, fanartCategoryEntity.categoryName, fanartEntity.title, userEntity.id,
                        userEntity.email, userEntity.nickName, fanartEntity.likeCount, fanartEntity.hitCount, imageEntity.imageUrl)
                .fetch();
    }

}
