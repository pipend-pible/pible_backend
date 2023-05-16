package com.pible.config.sercurity.service;

import com.pible.common.entity.AuthorityEntity;
import com.pible.common.entity.UserEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.config.sercurity.model.PibleUser;
import com.pible.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
        List<AuthorityEntity> authorityEntityList = userRepository.selectUserAuthorityList(userEntity.getId());

        if(CollectionUtils.isEmpty(authorityEntityList)) {
            throw new BusinessException(ResponseCode.FAIL);
        }

        List<SimpleGrantedAuthority> authorityList = authorityEntityList
                .stream()
                .map(authorityEntity -> new SimpleGrantedAuthority(authorityEntity.getAuthority()))
                .collect(Collectors.toList());

        return new PibleUser(userEntity, authorityList);
    }
}
