package com.eureca.egressos.mapper;

import com.eureca.egressos.dto.UserDto;
import com.eureca.egressos.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
          user.getId(),
          user.getUsername(),
          user.getEnrollment(),
          user.getCourseCode(),
          user.getType()
        );
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEnrollment(userDto.getEnrollment());
        user.setCourseCode(userDto.getCourseCode());
        user.setType(userDto.getType());
        return user;
    }
}
