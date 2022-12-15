package com.pible.service.mapping.dao;

import com.pible.common.entity.FanartTagMappingEntity;
import com.pible.common.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FanartTagMappingRepository extends JpaRepository<FanartTagMappingEntity, Long> {
    List<FanartTagMappingEntity> findAllByTagEntity(TagEntity tagEntity);
}
