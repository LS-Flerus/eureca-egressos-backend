package com.eureca.egressos.controller.documentation;

import com.eureca.egressos.dto.PlaqueDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Tag(name = "Placas", description = "Serviço para gerenciamento de placas de formatura")
public interface PlaqueController {

    @Operation(summary = "Criar nova placa")
    ResponseEntity<PlaqueDto> createPlaque(
            @RequestBody PlaqueDto plaqueDto,
            @RequestHeader("token-de-autenticacao") String tokenAS);

    @Operation(summary = "criando todas as placas possíveis")
    ResponseEntity<Void> createAllPlaques(
            @Parameter(name = "tokenAS", description = "Token do Eureca AS") String token);

    @Operation(summary = "Atualizar placa existente")
    ResponseEntity<PlaqueDto> updatePlaque(
            @Parameter(name = "id", description = "ID da placa", required = true)
            @PathVariable UUID id,
            @RequestBody PlaqueDto plaqueDto);

    @Operation(summary = "Deletar placa")
    ResponseEntity<Void> deletePlaque(@PathVariable UUID id);

    @Operation(summary = "Buscar placa por ID")
    ResponseEntity<PlaqueDto> getPlaqueById(@PathVariable UUID id);

    @Operation(summary = "Listar todas as placas")
    ResponseEntity<List<PlaqueDto>> getAllPlaques();

    @Operation(summary = "Listar todas as placas de um curso")
    ResponseEntity<Collection<?>> getPlaqueByCourseCode(@RequestParam String courseCode);
}