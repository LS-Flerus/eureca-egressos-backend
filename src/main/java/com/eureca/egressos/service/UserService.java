package com.eureca.egressos.service;

import com.eureca.egressos.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long Id);
}
