package com.pible.service.category.fanart.dao;

import com.pible.common.entity.FanartCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanartCategoryRepository extends JpaRepository<FanartCategoryEntity, Long> {
    
}
