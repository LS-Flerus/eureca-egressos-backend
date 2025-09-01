package com.eureca.egressos.service;

import com.eureca.egressos.dto.PlaqueSessionDto;
import com.eureca.egressos.model.PlaqueModel;
import com.eureca.egressos.model.PlaqueSessionModel;
import com.eureca.egressos.repository.PlaqueSessionRepository;
import com.eureca.egressos.service.interfaces.PlaqueSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PlaqueSessionServiceImpl implements PlaqueSessionService {

    private final PlaqueSessionRepository plaqueSessionRepository;

    @Autowired
    public PlaqueSessionServiceImpl(PlaqueSessionRepository plaqueSessionRepository) {
        this.plaqueSessionRepository = plaqueSessionRepository;
    }

    @Override
    @Transactional
    public PlaqueSessionDto createPlaqueSession(PlaqueSessionDto dto) {
        PlaqueSessionModel session = PlaqueSessionModel.fromDto(dto);
        plaqueSessionRepository.save(session);
        return session.toDto();
    }

    @Override
    @Transactional
    public PlaqueSessionDto updatePlaqueSession(UUID id, PlaqueSessionDto dto) {
        PlaqueSessionModel existing = plaqueSessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plaque session not found"));

        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getContent() != null) existing.setContent(dto.getContent());
        if (dto.getIsList() != null) existing.setIsList(dto.getIsList());

        if (dto.getPlaqueId() != null) {
            existing.setPlaque(PlaqueModel.builder().id(dto.getPlaqueId()).build());
        }

        return plaqueSessionRepository.save(existing).toDto();
    }

    @Override
    @Transactional
    public void deletePlaqueSession(UUID id) {
        plaqueSessionRepository.deleteById(id);
    }

    @Override
    public PlaqueSessionDto getPlaqueSessionById(UUID id) {
        return plaqueSessionRepository.findById(id)
                .map(PlaqueSessionModel::toDto)
                .orElseThrow(() -> new RuntimeException("Plaque session not found"));
    }

    @Override
    public List<PlaqueSessionDto> getSessionsByPlaqueId(UUID plaqueId) {
        return plaqueSessionRepository.findByPlaqueId(plaqueId)
                .stream()
                .map(PlaqueSessionModel::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteSessionsByPlaqueId(UUID plaqueId) {
        List<PlaqueSessionModel> sessions = plaqueSessionRepository.findByPlaqueId(plaqueId);
        plaqueSessionRepository.deleteAll(sessions);
    }

    @Override
    public List<PlaqueSessionDto> getAllPlaqueSessions() {
        return plaqueSessionRepository.findAll()
                .stream()
                .map(PlaqueSessionModel::toDto)
                .toList();
    }
}