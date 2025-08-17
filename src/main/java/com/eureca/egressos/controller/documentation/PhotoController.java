package com.eureca.egressos.controller.documentation;

import com.eureca.egressos.dto.PhotoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Fotos", description = "Gerenciamento de fotos das placas")
public interface PhotoController {

    @Operation(summary = "Criar foto")
    ResponseEntity<PhotoDto> createPhoto(@RequestBody PhotoDto photoDto);

    @Operation(summary = "Atualizar foto")
    ResponseEntity<PhotoDto> updatePhoto(@PathVariable UUID id, @RequestBody PhotoDto photoDto);

    @Operation(summary = "Deletar foto")
    ResponseEntity<Void> deletePhoto(@PathVariable UUID id);

    @Operation(summary = "Buscar foto por ID")
    ResponseEntity<PhotoDto> getPhotoById(@PathVariable UUID id);

    @Operation(summary = "Listar todas as fotos")
    ResponseEntity<List<PhotoDto>> getAllPhotos();

    @Operation(summary = "Buscar fotos por ID da placa")
    ResponseEntity<List<PhotoDto>> getPhotosByPlaqueId(@PathVariable UUID plaqueId);
}