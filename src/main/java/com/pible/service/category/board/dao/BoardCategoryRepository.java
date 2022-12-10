package com.pible.service.category.board.dao;

import com.pible.common.entity.BoardCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategoryEntity, Long> {
    
}
