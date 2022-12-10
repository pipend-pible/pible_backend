package com.pible.service.fanart.dao;

import com.pible.common.entity.FanartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanartRepository extends JpaRepository<FanartEntity, Long> {
    
}
