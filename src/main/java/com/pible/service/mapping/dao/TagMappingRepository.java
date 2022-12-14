package com.pible.service.mapping.dao;

import com.pible.common.entity.TagEntity;
import com.pible.common.entity.TagMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMappingRepository extends JpaRepository<TagMappingEntity, Long> {
    List<TagMappingEntity> findAllByTagEntity(TagEntity tagEntity);
}
