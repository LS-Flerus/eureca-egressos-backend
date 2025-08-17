package com.eureca.egressos.controller;

import com.eureca.egressos.controller.documentation.PlaqueSessionController;
import com.eureca.egressos.dto.PlaqueSessionDto;
import com.eureca.egressos.service.interfaces.PlaqueSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/plaqueSessions")
public class PlaqueSessionControllerImpl implements PlaqueSessionController {

    private final PlaqueSessionService plaqueSessionService;

    public PlaqueSessionControllerImpl(PlaqueSessionService plaqueSessionService) {
        this.plaqueSessionService = plaqueSessionService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<PlaqueSessionDto> createPlaqueSession(@RequestBody PlaqueSessionDto dto) {
        return ResponseEntity.ok(plaqueSessionService.createPlaqueSession(dto));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<PlaqueSessionDto> updatePlaqueSession(@RequestParam UUID id, @RequestBody PlaqueSessionDto dto) {
        return ResponseEntity.ok(plaqueSessionService.updatePlaqueSession(id, dto));
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePlaqueSession(@RequestParam UUID id) {
        plaqueSessionService.deletePlaqueSession(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/getById")
    public ResponseEntity<PlaqueSessionDto> getPlaqueSessionById(@RequestParam UUID id) {
        return ResponseEntity.ok(plaqueSessionService.getPlaqueSessionById(id));
    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<List<PlaqueSessionDto>> getAllPlaqueSessions() {
        return ResponseEntity.ok(plaqueSessionService.getAllPlaqueSessions());
    }

    @Override
    @GetMapping("/getAllByPlaque")
    public ResponseEntity<List<PlaqueSessionDto>> getPlaqueSessionsByPlaqueId(@RequestParam UUID plaqueId) {
        return ResponseEntity.ok(plaqueSessionService.getSessionsByPlaqueId(plaqueId));
    }
}