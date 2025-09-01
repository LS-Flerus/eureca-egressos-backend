package com.eureca.egressos.service.interfaces;

import com.eureca.egressos.dto.PlaqueDto;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface PlaqueService {
    PlaqueDto createPlaque(PlaqueDto plaqueDto, String tokenAS);
    PlaqueDto updatePlaque(UUID id, PlaqueDto plaqueDto);
    void deletePlaque(UUID id);
    PlaqueDto getPlaqueById(UUID id);
    List<PlaqueDto> getAllPlaques();
    List<PlaqueDto> getAllPlaquesByCourse(String courseCode);
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
