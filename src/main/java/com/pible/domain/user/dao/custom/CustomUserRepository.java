package com.pible.domain.user.dao.custom;

import com.pible.common.entity.AuthorityEntity;

import java.util.List;

public interface CustomUserRepository {
    List<AuthorityEntity> selectUserAuthorityList(Long userId);
}
