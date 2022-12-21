package com.pible.domain.claim.fanart.dao;

import com.pible.common.entity.FanartClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanartClaimRepository extends JpaRepository<FanartClaimEntity, Long> {

}
