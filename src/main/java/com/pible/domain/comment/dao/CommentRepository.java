package com.pible.domain.comment.dao;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.CommentEntity;
import com.pible.common.entity.FanartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBoardEntity(BoardEntity boardEntity);
    List<CommentEntity> findAllByFanartEntity(FanartEntity fanartEntity);
}
