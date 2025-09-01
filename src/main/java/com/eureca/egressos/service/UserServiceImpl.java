package com.eureca.egressos.service;

import com.eureca.egressos.dto.UserDto;
import com.eureca.egressos.model.PlaqueModel;
import com.eureca.egressos.model.UserModel;
import com.eureca.egressos.repository.UserRepository;
import com.eureca.egressos.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDTO) {
        PlaqueModel plaque = null;
        if (userDTO.getPlaqueId() != null) {
            plaque = PlaqueModel.builder().id(userDTO.getPlaqueId()).build();
        }
        UserModel user = UserModel.builder()
                .name(userDTO.getName())
                .enrollment(userDTO.getEnrollment())
                .courseCode(userDTO.getCourseCode())
                .plaque(plaque)
                .build();

        userRepository.save(user);
        return user.toDto();
    }

    @Override
    @Transactional
    public UserDto updateUser(UUID id, UserDto userDto) {
        UserModel existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        existing.setName(userDto.getName());
        existing.setEnrollment(userDto.getEnrollment());
        existing.setCourseCode(userDto.getCourseCode());

        return userRepository.save(existing).toDto();
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUserById(UUID id) {
        return userRepository.findById(id)
                .map(UserModel::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserModel::toDto)
                .toList();
    }

    @Override
    public List<UserDto> getAllUsersByCourseCode(String courseCode) {
        return userRepository.getAllByCourseCode(courseCode).stream()
                .map(UserModel::toDto)
                .toList();
    }

    @Override
    public UserDto getUserByEnrollment(String enrollment) {
        UserModel user = userRepository.findByEnrollment(enrollment)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + enrollment));

        return user.toDto();
    }

}
