package com.pible.domain.fanart.dao;

import com.pible.common.entity.FanartEntity;
import com.pible.domain.fanart.dao.custom.CustomFanartRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanartRepository extends JpaRepository<FanartEntity, Long>, CustomFanartRepository {
    
}
