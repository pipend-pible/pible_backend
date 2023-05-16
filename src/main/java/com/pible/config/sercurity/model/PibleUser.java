package com.pible.config.sercurity.model;

import com.pible.common.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@Setter
public class PibleUser extends User {
    private String userNickName;

    public PibleUser(UserEntity userEntity, List<SimpleGrantedAuthority> authorityList) {
        super(userEntity.getEmail(), userEntity.getPassword(), authorityList);
        this.userNickName = userEntity.getNickName();
    }

    public PibleUser(String email, String userPassword, List<GrantedAuthority> authorities, String userNickName) {
        super(email, userPassword, authorities);
        this.userNickName = userNickName;
    }
}


