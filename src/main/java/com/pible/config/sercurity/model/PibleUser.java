package com.pible.config.sercurity.model;

import com.pible.common.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class PibleUser extends User {
    private String userNickName;
    private Long userId;

    public PibleUser(UserEntity userEntity, List<SimpleGrantedAuthority> authorityList) {
        super(userEntity.getEmail(), userEntity.getPassword(), authorityList);
        this.userNickName = userEntity.getNickName();
        this.userId = userEntity.getId();
    }

    public PibleUser(String email, String userPassword, Collection<GrantedAuthority> authorities, String userNickName, Long userId) {
        super(email, userPassword, authorities);
        this.userNickName = userNickName;
        this.userId = userId;
    }
}


