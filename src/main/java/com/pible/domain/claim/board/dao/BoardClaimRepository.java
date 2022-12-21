package com.pible.domain.claim.board.dao;

import com.pible.common.entity.BoardClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardClaimRepository extends JpaRepository<BoardClaimEntity, Long> {

}
