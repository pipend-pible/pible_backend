package com.pible.domain.user.service.impl;

import com.pible.common.email.EmailSender;
import com.pible.common.entity.AuthorityEntity;
import com.pible.common.entity.UserAuthorityEntity;
import com.pible.common.entity.UserEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.config.sercurity.enums.Authority;
import com.pible.config.sercurity.utils.JwtUtils;
import com.pible.domain.user.dao.AuthorityRepository;
import com.pible.domain.user.dao.UserAuthorityRepository;
import com.pible.domain.user.dao.UserRepository;
import com.pible.domain.user.mapper.UserMapper;
import com.pible.domain.user.model.UserDto;
import com.pible.domain.user.model.VerifyCodeDto;
import com.pible.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import javax.cache.Cache;
import javax.cache.CacheManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.pible.common.Constants.EHCACHE_KEY_NAME;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final AuthorityRepository authorityRepository;
    private final JwtUtils jwtUtils;
    private final CacheManager cacheManager;
    private final EmailSender emailSender;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public boolean checkDuplicateNickName(String nickName) {
        return userRepository.findByNickName(nickName).isPresent();
    }

    @Override
    public boolean checkDuplicateUserName(String userName) {
        return userRepository.findByEmail(userName).isPresent();
    }

    // 회원가입 검증용 jwt를 발행합니다.
    @Override
    public String generateSignUpToken(String userName) {
        return jwtUtils.createToken(userName, null, 3 * 60 * 1000);
    }

    // 회원가입 검증을 위해 이메일로 보내지는 코드값을 어플리케이션 캐시에 저장합니다.
    @Override
    @Cacheable(value = EHCACHE_KEY_NAME, key = "#userName")
    public String generateEmailAuthStr(String userName) {
        String emailAuthStr = RandomStringUtils.randomAlphanumeric(5);

        emailSender.send(userName, emailAuthStr);

        return emailAuthStr;
    }

    @Override
    public boolean verifyEmailAuthStr(VerifyCodeDto verifyCodeDto) {
        String userName = verifyCodeDto.getEmail();
        String verifyCode = verifyCodeDto.getVerifyCode();

        Cache<String, String> cache = getCache();
        if(cache == null || cache.get(userName) == null) {
        // TODO exception 추가가 필요할 수 있습니다.
            return false;
        }

        boolean isSuccess = cache.get(userName).equals(verifyCode);

        // 캐시에 저장된 코드값과 동일할 경우 인증이 되었기때문에 캐시를 무효화합니다.
        if(isSuccess) {
            cache.remove(userName);
        }

        return isSuccess;
    }

    @Override
    @Transactional
    public void signUp(UserDto userDto) {
        Cache<String, String> cache = getCache();

        if(StringUtils.isNotEmpty(cache.get(userDto.getUsername()))) {
            throw new BusinessException(ResponseCode.NOT_AUTHORIZED_EMAIL);
        }

        userRepository.findByEmail(userDto.getUsername()).ifPresent((userEntity) -> {
            throw new BusinessException(ResponseCode.DUPLICATED_USER);
        });

        UserEntity userBeforeEntity = userMapper.dtoToEntity(userDto);
        userBeforeEntity.setUserEmail(userDto.getUsername());

        UserEntity userEntity = userRepository.save(userBeforeEntity);
        AuthorityEntity authorityEntity = authorityRepository.getReferenceById(Authority.USER.name());

        userAuthorityRepository.save(new UserAuthorityEntity(userEntity, authorityEntity));
    }

    private Cache<String, String> getCache() {
        return cacheManager.getCache(EHCACHE_KEY_NAME, String.class, String.class);
    }
}
