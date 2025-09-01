package com.eureca.egressos.service.interfaces;

import com.eureca.egressos.dto.PlaqueSessionDto;

import java.util.List;
import java.util.UUID;

public interface PlaqueSessionService {
    PlaqueSessionDto createPlaqueSession(PlaqueSessionDto dto);
    PlaqueSessionDto updatePlaqueSession(UUID id, PlaqueSessionDto dto);
    void deletePlaqueSession(UUID id);
    void deleteSessionsByPlaqueId(UUID plaqueId);
    PlaqueSessionDto getPlaqueSessionById(UUID id);
    List<PlaqueSessionDto> getSessionsByPlaqueId(UUID plaqueId);
    List<PlaqueSessionDto> getAllPlaqueSessions();
}