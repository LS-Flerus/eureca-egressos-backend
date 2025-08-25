package com.eureca.egressos.controller;

import com.eureca.egressos.controller.documentation.UserController;
import com.eureca.egressos.dto.UserDto;
import com.eureca.egressos.service.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestParam UUID id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/getById")
    public ResponseEntity<UserDto> getUserById(@RequestParam UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    @GetMapping("/getAllByCourseCode")
    public ResponseEntity<List<UserDto>> getAllUsersByCourseCode(@RequestParam String courseCode) {
        return ResponseEntity.ok(userService.getAllUsersByCourseCode(courseCode));
    }

    @GetMapping("/getLoggedUser")
    public ResponseEntity<UserDto> getLoggedUser(
            Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getLoggedUser(authentication.getName()));
    }
}