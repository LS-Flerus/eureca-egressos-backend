package com.eureca.egressos.controller.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.eureca.egressos.dto.user.UserCreateRequestDto;
import com.eureca.egressos.dto.user.UserResponseDto;
import org.springframework.security.core.Authentication;

@Tag(name = "Usuário", description = "Serviço de Usuário")
public interface UserController {
    @Operation(description = "Registrar usuário", summary = "Registrar novo usuário no sistema")
    ResponseEntity<String> createUser(
            @RequestBody UserCreateRequestDto userDto);

    @Operation(description = "Deletar usuário", summary = "Remover usuário do sistema.")
    ResponseEntity<String> deleteUser(Authentication authentication);

    @Operation(description = "Ler usuário", summary = "Pegar informações do usuário no sistema.")
    ResponseEntity<UserResponseDto> getUser(Authentication authentication);
}
