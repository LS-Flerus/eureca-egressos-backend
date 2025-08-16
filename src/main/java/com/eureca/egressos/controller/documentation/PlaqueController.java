package com.eureca.egressos.controller.documentation;

import com.eureca.egressos.dto.plaque.PlaqueCreateRequestDto;
import com.eureca.egressos.dto.plaque.PlaqueResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Tag(name = "Placas", description = "Serviço de Placas")
public interface PlaqueController {
    @Operation(description = "Registrar placa", summary = "Registrar nova placa no sistema")
    ResponseEntity<String> createPlaque(@RequestBody PlaqueCreateRequestDto plaqueDto);

    @Operation(description = "Deletar placa", summary = "Remover placa do sistema.")
    ResponseEntity<String> deletePlaque(@RequestParam("id") int id);

    @Operation(description = "Encontrar placa pelo id", summary = "Pegar informações de placa pelo id dela no sistema.")
    ResponseEntity<PlaqueResponseDto> getPlaqueById(@RequestParam("id") int id);

    //@Operation(description = "Encontrar placa com filtro", summary = "Pega as placas que se adequam aos filtros passados")
    //ResponseEntity<Collection<PlaqueResponseDto>> listPlaqueByFilter();

    @Operation(description = "Encontrar placa com filtro", summary = "Pega as placas que se adequam aos filtros passados")
    ResponseEntity<Collection<?>> listPlaqueByFilter(
            @Parameter(
                    name = "startSemester",
                    description = "Período do qual começa a busca"
            )
            @RequestParam("startSemester") String startSemester,

            @Parameter(
                    name = "endSemester",
                    description = "Período no qua termina a busca"
            )
            @RequestParam("endSemester") String endSemester,

            @Parameter(
                    name = "courseCode",
                    description = "Código do curso do qual se deseja as placas"
            )
            @RequestParam("courseCode") String courseCode,

            @Parameter(
                    name = "className",
                    description = "Nome da turma que está sendo buscada"
            )
            @RequestParam("className") String className,

            @Parameter(
                    name = "approved",
                    description = "Status se a placa foi ou não aprovada pela coordenação"
            )
            @RequestParam("approved") boolean approved,

            @Parameter(
                    name = "toApprove",
                    description = "Status dizendo se a placa está ou não com a aprovação pendente"
            )
            @RequestParam("toApprove") boolean toApprove,

            @Parameter(
                    name = "campus",
                    description = "Campus ao qual a turma pertence"
            )
            @RequestParam("campus") int campus,

            @Parameter(
                    name = "studentName",
                    description = "Nome de um dos estudantes concluintes da turma"
            )
            @RequestParam("studentName") String studentName
    );
}