package com.pible.domain.mapping.dao;

import com.pible.common.entity.TagEntity;
import com.pible.common.entity.BoradTagMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardTagMappingRepository extends JpaRepository<BoradTagMappingEntity, Long> {
    List<BoradTagMappingEntity> findAllByTagEntity(TagEntity tagEntity);
}
