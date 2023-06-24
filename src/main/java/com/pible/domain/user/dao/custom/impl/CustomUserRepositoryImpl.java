package com.pible.domain.user.dao.custom.impl;

import com.pible.common.entity.AuthorityEntity;
import com.pible.common.entity.UserEntity;
import com.pible.domain.user.dao.custom.CustomUserRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.pible.common.entity.QAuthorityEntity.authorityEntity;
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
                                authorityEntity.authority,
                                authorityEntity.authCd,
                                authorityEntity.createDate
                        )
                )
                .from(authorityEntity)
                .join(userAuthorityEntity)
                    .on(userAuthorityEntity.authorityEntity.authority.eq(authorityEntity.authority))
                .join(userEntity)
                    .on(userEntity.id.eq(userAuthorityEntity.userEntity.id))
                .where(userEntity.id.eq(userId))
                .fetch();
    }
}
