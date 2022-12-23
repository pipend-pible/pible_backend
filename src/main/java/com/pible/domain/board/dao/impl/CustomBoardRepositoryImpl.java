package com.pible.domain.board.dao.impl;

import com.pible.common.entity.BoardEntity;
import com.pible.domain.board.dao.custom.CustomBoardRepository;
import com.pible.domain.channel.model.ContentRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.pible.common.entity.QBoardEntity.boardEntity;
import static com.pible.common.entity.QChannelEntity.channelEntity;
import static com.pible.common.entity.QUserEntity.userEntity;

public class CustomBoardRepositoryImpl extends QuerydslRepositorySupport implements CustomBoardRepository {
    private final JPAQueryFactory queryFactory;

    public CustomBoardRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BoardEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ContentRes> findAllByChannel(Long channelId) {
        return queryFactory.select(
                Projections.bean(ContentRes.class,
                        channelEntity.id,
                        boardEntity.id,
                        null,
                        channelEntity.category,
                        boardEntity.title,
                        userEntity.email,
                        userEntity.nickName,
                        boardEntity.likeCount,
                        boardEntity.hitCount
                )
        )
                .from(boardEntity)
                .join(boardEntity.channelEntity, channelEntity)
                .join(boardEntity.userEntity, userEntity)
                .on(channelEntity.id.eq(channelId))
//                .where()
                .fetch();
    }
}
