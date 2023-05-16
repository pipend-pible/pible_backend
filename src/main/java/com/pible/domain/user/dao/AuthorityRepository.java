package com.pible.domain.user.dao;

import com.pible.common.entity.AuthorityEntity;
import com.pible.domain.user.dao.custom.CustomUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, String>, CustomUserRepository {

}
