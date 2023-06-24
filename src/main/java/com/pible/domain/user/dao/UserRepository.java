package com.pible.domain.user.dao;

import com.pible.common.entity.UserEntity;
import com.pible.domain.user.dao.custom.CustomUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, CustomUserRepository {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByNickName(String nickName);
}
