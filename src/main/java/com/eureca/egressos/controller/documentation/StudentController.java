package com.eureca.egressos.controller.documentation;

import com.eureca.egressos.dto.StudentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Estudantes", description = "Serviço para gerenciamento dos estudantes vinculados às placas")
public interface StudentController {

    @Operation(summary = "Criar estudante")
    ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto);

    @Operation(summary = "Atualizar estudante")
    ResponseEntity<StudentDto> updateStudent(@PathVariable UUID id, @RequestBody StudentDto studentDto);

    @Operation(summary = "Deletar estudante")
    ResponseEntity<Void> deleteStudent(@PathVariable UUID id);

    @Operation(summary = "Buscar estudante por ID")
    ResponseEntity<StudentDto> getStudentById(@PathVariable UUID id);

    @Operation(summary = "Listar todos os estudantes")
    ResponseEntity<List<StudentDto>> getAllStudents();
}
