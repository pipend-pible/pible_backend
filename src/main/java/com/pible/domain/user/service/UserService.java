package com.pible.domain.user.service;

import com.pible.domain.user.model.UserDto;

public interface UserService {
    void signUp(UserDto userDto);
    String login(UserDto userDto);
}
