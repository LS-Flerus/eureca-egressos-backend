package com.eureca.egressos.controller;

import com.eureca.egressos.controller.documentation.PlaqueController;
import com.eureca.egressos.dto.PlaqueDto;
import com.eureca.egressos.service.interfaces.PlaqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/plaque")
@CrossOrigin
@Validated
public class PlaqueControllerImpl implements PlaqueController {
    private final PlaqueService plaqueService;

    public PlaqueControllerImpl(PlaqueService plaqueService) {
        this.plaqueService = plaqueService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<PlaqueDto> createPlaque(@RequestBody PlaqueDto plaqueDto, @RequestHeader String tokenAS) {
        return ResponseEntity.ok(plaqueService.createPlaque(plaqueDto, tokenAS));
    }

    @Override
    @PostMapping("/createAll")
    public ResponseEntity<Void> createAllPlaques(@RequestParam String tokenAS) {
        plaqueService.creatingAndPraying(tokenAS);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<PlaqueDto> updatePlaque(@RequestParam UUID id, @RequestBody PlaqueDto plaqueDto) {
        return ResponseEntity.ok(plaqueService.updatePlaque(id, plaqueDto));
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePlaque(@RequestParam UUID id) {
        plaqueService.deletePlaque(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/getById")
    public ResponseEntity<PlaqueDto> getPlaqueById(@RequestParam(value = "id") UUID id) {
        return ResponseEntity.ok(plaqueService.getPlaqueById(id));
    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<List<PlaqueDto>> getAllPlaques() {
        return ResponseEntity.ok(plaqueService.getAllPlaques());
    }

    @Override
    @GetMapping("/getByCourseCode")
    public ResponseEntity<Collection<?>> getPlaqueByCourseCode(@RequestParam String courseCode) {
        return ResponseEntity.ok(plaqueService.getAllPlaquesByCourse(courseCode));
    }

    @GetMapping("/getByFilter")
    public ResponseEntity<Collection<?>> getAllWithMetricsByFilters(
            @RequestParam(value = "startSemester", required = false) String startSemester,
            @RequestParam(value = "endSemester", required = false) String endSemester,
            @RequestParam(value = "courseCode", required = false) String courseCode,
            @RequestParam(value = "className", required = false) String className,
            @RequestParam(value = "approved", required = false) boolean approved,
            @RequestParam(value = "toApprove", required = false) boolean toApprove,
            @RequestParam(value = "campus", required = false) String campus,
            @RequestParam(value = "studentName", required = false) String studentName
    ) throws Exception {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.plaqueService.listPlaqueByFilter(startSemester,
                        endSemester,
                        courseCode,
                        className,
                        approved,
                        toApprove,
                        campus,
                        studentName));
    }
}
