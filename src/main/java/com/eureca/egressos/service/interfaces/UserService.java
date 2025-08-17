package com.eureca.egressos.service.interfaces;

import com.eureca.egressos.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto dto);
    UserDto updateUser(UUID id, UserDto dto);
    void deleteUser(UUID id);
    UserDto getUserById(UUID id);
    List<UserDto> getAllUsers();
}
