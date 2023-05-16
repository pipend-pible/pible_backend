package com.pible.domain.user.service.impl;

import com.pible.common.entity.AuthorityEntity;
import com.pible.common.entity.UserAuthorityEntity;
import com.pible.common.entity.UserEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.config.sercurity.utils.JwtUtils;
import com.pible.config.sercurity.enums.Authority;
import com.pible.config.sercurity.model.PibleUser;
import com.pible.domain.user.dao.AuthorityRepository;
import com.pible.domain.user.dao.UserAuthorityRepository;
import com.pible.domain.user.dao.UserRepository;
import com.pible.domain.user.mapper.UserMapper;
import com.pible.domain.user.model.UserDto;
import com.pible.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final AuthorityRepository authorityRepository;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public void signUp(UserDto userDto) {
        userRepository.findByEmail(userDto.getUsername()).ifPresent((userEntity) -> {
            throw new BusinessException(ResponseCode.FAIL);
        });

        UserEntity userBeforeEntity = userMapper.dtoToEntity(userDto);
        userBeforeEntity.setUserEmail(userDto.getUsername());

        UserEntity userEntity = userRepository.save(userBeforeEntity);
        AuthorityEntity authorityEntity = authorityRepository.getReferenceById(Authority.USER.name());

        userAuthorityRepository.save(new UserAuthorityEntity(userEntity, authorityEntity));
    }

    @Override
    public String login(UserDto userDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof PibleUser)) {
            throw new BusinessException(ResponseCode.FAIL);
        }

        PibleUser pibleUser = (PibleUser) principal;
        Map<String, Object> claimMap = new HashMap<>();

        claimMap.put("nickName", pibleUser.getUserNickName());
        claimMap.put("authorities", pibleUser.getAuthorities());

        return jwtUtils.createToken(pibleUser.getUsername(), claimMap);
    }
}
