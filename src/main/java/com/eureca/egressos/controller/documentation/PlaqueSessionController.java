package com.eureca.egressos.controller.documentation;

import com.eureca.egressos.dto.PlaqueSessionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Sessões de Placas", description = "Gerenciamento de sessões de placas")
public interface PlaqueSessionController {

    @Operation(summary = "Criar nova sessão de placa")
    ResponseEntity<PlaqueSessionDto> createPlaqueSession(@RequestBody PlaqueSessionDto dto);

    @Operation(summary = "Atualizar sessão de placa existente")
    ResponseEntity<PlaqueSessionDto> updatePlaqueSession(
            @Parameter(name = "id", description = "ID da sessão", required = true)
            @PathVariable UUID id,
            @RequestBody PlaqueSessionDto dto);

    @Operation(summary = "Deletar sessão de placa")
    ResponseEntity<Void> deletePlaqueSession(@PathVariable UUID id);

    @Operation(summary = "Buscar sessão por ID")
    ResponseEntity<PlaqueSessionDto> getPlaqueSessionById(@PathVariable UUID id);

    @Operation(summary = "Listar todas as sessões de placas")
    ResponseEntity<List<PlaqueSessionDto>> getAllPlaqueSessions();

    @Operation(summary = "Buscar sessões por ID da placa")
    ResponseEntity<List<PlaqueSessionDto>> getPlaqueSessionsByPlaqueId(@PathVariable UUID plaqueId);
}