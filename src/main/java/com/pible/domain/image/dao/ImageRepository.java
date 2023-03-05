package com.pible.domain.image.dao;

import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.FanartEntity;
import com.pible.common.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findAllByBoardEntity(BoardEntity boardEntity);
    List<ImageEntity> findAllByFanartEntity(FanartEntity fanartEntity);
    
}
