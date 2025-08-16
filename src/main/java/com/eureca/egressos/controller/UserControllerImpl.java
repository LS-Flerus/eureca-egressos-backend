package com.eureca.egressos.controller;

import com.eureca.egressos.controller.documentation.UserController;
import com.eureca.egressos.dto.UserDto;
import com.eureca.egressos.dto.user.UserCreateRequestDto;
import com.eureca.egressos.dto.user.UserResponseDto;
import com.eureca.egressos.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Validated
public class UserControllerImpl implements UserController {
    private final UserService userService;
    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Validated @RequestBody UserCreateRequestDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(
            Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(authentication.getName()));
    }

    @GetMapping("/get")
    public ResponseEntity<UserResponseDto> getUser(
            Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(authentication.getName()));
    }
}
