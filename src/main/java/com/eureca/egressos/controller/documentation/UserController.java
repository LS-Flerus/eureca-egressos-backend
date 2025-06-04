package com.eureca.egressos.controller.documentation;

import com.eureca.egressos.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Usuário", description = "Serviço de Usuário")
public interface UserController {

    @Operation(description = "Cadastrar usuário", summary = "Cadastrar novo usuário criado pelo coordenador")
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto);
}
