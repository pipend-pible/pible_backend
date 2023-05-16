package com.pible.domain.user.dao.custom.impl;

import com.pible.common.entity.AuthorityEntity;
import com.pible.common.entity.UserEntity;
import com.pible.domain.user.dao.custom.CustomUserRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.pible.common.entity.QAuthorityEntity.authorityEntity;
import static com.pible.common.entity.QChannelEntity.channelEntity;
import static com.pible.common.entity.QFanartEntity.fanartEntity;
import static com.pible.common.entity.QTagEntity.tagEntity;
import static com.pible.common.entity.QUserAuthorityEntity.userAuthorityEntity;
import static com.pible.common.entity.QUserEntity.userEntity;

public class CustomUserRepositoryImpl extends QuerydslRepositorySupport implements CustomUserRepository {
    private final JPAQueryFactory queryFactory;

    public CustomUserRepositoryImpl(JPAQueryFactory queryFactory) {
        super(UserEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<AuthorityEntity> selectUserAuthorityList(Long userId) {
        return queryFactory.select(
                        Projections.constructor(AuthorityEntity.class,
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
                .from(authorityEntity)
                .join(userAuthorityEntity.authorityEntity, authorityEntity)
                .join(userEntity, userAuthorityEntity.userEntity)
                .where(userEntity.id.eq(userId))
                .fetch();
    }
}
