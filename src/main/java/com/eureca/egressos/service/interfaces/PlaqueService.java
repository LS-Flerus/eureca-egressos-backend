package com.eureca.egressos.service.interfaces;

import com.eureca.egressos.dto.PlaqueDto;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface PlaqueService {
    PlaqueDto createPlaque(PlaqueDto plaqueDto);
    PlaqueDto updatePlaque(UUID id, PlaqueDto plaqueDto);
    void deletePlaque(UUID id);
    PlaqueDto getPlaqueById(UUID id);
    List<PlaqueDto> getAllPlaques();
    Collection<PlaqueDto> listPlaqueByFilter(
            String startSemester,
            String endSemester,
            String courseCode,
            String className,
            Boolean approved,
            Boolean toApprove,
            String campus,
            String studentName
            );
}
