package com.eureca.egressos.controller.documentation;

import com.eureca.egressos.dto.dasScao.ScaoStudentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Eureca", description = "Serviço para consulta de egressos Eureca")
public interface EurecaController {

    @Operation(
            summary = "Buscar egressos por curso e semestre",
            description = "Retorna a lista de estudantes egressos filtrados pelo código do curso e código do currículo. "
                    + "No futuro, poderá aceitar também filtros como ano de ingresso e semestre de conclusão."
    )
    ResponseEntity<List<ScaoStudentDto>> getAlumniByCourseAndSemester(
            @Parameter(
                    name = "courseCode",
                    description = "Código numérico do curso (obrigatório)",
                    required = true
            )
            @RequestParam("courseCode") Integer courseCode,

            @Parameter(
                    name = "startSemester",
                    description = "Período a partir do qual começa a pesquisa (obrigatório)",
                    required = true
            )
            @RequestParam("startSemester") String startSemester,

            @Parameter(
                    name = "endSemester",
                    description = "Período no qual termina a pesquisa (obrigatório)",
                    required = true
            )
            @RequestParam("endSemester") String endSemester,

            @Parameter(
                    name = "tokenAS",
                    description = "Token do Eureca AS",
                    in = ParameterIn.HEADER
            )
            @RequestHeader("tokenAS") String authToken
    );
}