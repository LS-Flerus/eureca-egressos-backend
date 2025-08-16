package com.eureca.egressos.controller;

import com.eureca.egressos.dto.plaque.PlaqueCreateRequestDto;
import com.eureca.egressos.dto.plaque.PlaqueResponseDto;
import com.eureca.egressos.dto.user.UserCreateRequestDto;
import com.eureca.egressos.dto.user.UserResponseDto;
import com.eureca.egressos.service.interfaces.PlaqueService;
import com.eureca.egressos.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/plaque")
@CrossOrigin
@Validated
public class PlaqueControllerImpl {
    private final PlaqueService plaqueService;
    @Autowired
    public PlaqueControllerImpl(PlaqueService plaqueService) {
        this.plaqueService = plaqueService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPlaque(@Validated @RequestBody PlaqueCreateRequestDto plaqueDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(plaqueService.createPlaque(plaqueDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePlaque(
            @RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(plaqueService.deletePlaque(id));
    }

    @GetMapping("/get")
    public ResponseEntity<PlaqueResponseDto> getPlaque(
            @RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(plaqueService.getPlaqueById(id));
    }

    @GetMapping("/getWithParameters")
    public ResponseEntity<Collection<?>> getAllWithMetricsByFilters(
            @RequestParam(value = "periodo_de", required = false) String startSemester,
            @RequestParam(value = "periodo_ate", required = false) String endSemester,
            @RequestParam(value = "codigo_curso", required = false) String courseCode,
            @RequestParam(value = "nome_turma", required = false) String className,
            @RequestParam(value = "aprovada", required = false) boolean approved,
            @RequestParam(value = "para_aprovacao", required = false) boolean toApproved,
            @RequestParam(value = "campus", required = false) Integer campus,
            @RequestParam(value = "nome_estudante", required = false) String studentName
    ) throws Exception {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.studentGetAllService.getAllWithMetricsByFilters(campus,
                        startSemester,
                        endSemester,
                        courseCode,
                        className,
                        approved,
                        toApproved,
                        studentName));
    }
}
