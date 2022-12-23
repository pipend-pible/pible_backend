package com.pible.domain.board.dao;

import com.pible.common.entity.BoardEntity;
import com.pible.domain.board.dao.custom.CustomBoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, CustomBoardRepository {
    
}
