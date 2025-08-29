package com.eureca.egressos.controller.documentation;

import com.eureca.egressos.dto.UserDto;
import com.eureca.egressos.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Usuários", description = "Serviço para gerenciamento de usuários da comissão de formatura")
public interface UserController {

    @Operation(summary = "Criar novo usuário")
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto);

    @Operation(summary = "Atualizar usuário existente")
    ResponseEntity<UserDto> updateUser(
            @Parameter(name = "id", description = "ID do usuário", required = true)
            @PathVariable UUID id,
            @RequestBody UserDto userDto);

    @Operation(summary = "Deletar usuário")
    ResponseEntity<Void> deleteUser(
            @Parameter(name = "id", description = "ID do usuário", required = true)
            @PathVariable UUID id);

    @Operation(summary = "Buscar usuário por ID")
    ResponseEntity<UserDto> getUserById(
            @Parameter(name = "id", description = "ID do usuário", required = true)
            @PathVariable UUID id);

    @Operation(summary = "Listar todos os usuários")
    ResponseEntity<List<UserDto>> getAllUsers();

    @Operation(summary = "Listar todos os usuários pelo código do curso")
    ResponseEntity<List<UserDto>> getAllUsersByCourseCode(@RequestParam String courseCode);

    @Operation(description = "Ler usuário logado", summary = "Pegar informações do usuário atualmente logado no sistema.")
    ResponseEntity<UserDto> getUserByEnrollment(
            @Parameter(name = "enrollment", description = "matricula do estudante", required = true)
            @PathVariable String enrollment);
}
