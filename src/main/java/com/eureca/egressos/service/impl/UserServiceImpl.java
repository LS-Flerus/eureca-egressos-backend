package com.eureca.egressos.service.impl;

import com.eureca.egressos.dto.UserDto;
import com.eureca.egressos.entity.User;
import com.eureca.egressos.mapper.UserMapper;
import com.eureca.egressos.repository.UserRepository;
import com.eureca.egressos.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        System.out.println("Usuário salvo com ID: " + savedUser.getId());
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return null;
    }
}
