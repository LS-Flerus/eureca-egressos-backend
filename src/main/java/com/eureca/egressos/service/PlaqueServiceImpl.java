package com.eureca.egressos.service;

import com.eureca.egressos.dto.PlaqueDto;
import com.eureca.egressos.dto.UserDto;
import com.eureca.egressos.model.PlaqueModel;
import com.eureca.egressos.model.UserModel;
import com.eureca.egressos.repository.PlaqueRepository;
import com.eureca.egressos.service.interfaces.PlaqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlaqueServiceImpl implements PlaqueService {

    private final PlaqueRepository plaqueRepository;

    @Autowired
    public PlaqueServiceImpl(PlaqueRepository plaqueRepository) {
        this.plaqueRepository = plaqueRepository;
    }

    @Override
    @Transactional
    public PlaqueDto createPlaque(PlaqueDto plaqueDto) {
        PlaqueModel plaque = PlaqueModel.fromDto(plaqueDto);
        plaque = plaqueRepository.save(plaque);
        return plaque.toDto();
    }

    @Override
    @Transactional
    public PlaqueDto updatePlaque(UUID id, PlaqueDto plaqueDto) {
        PlaqueModel existing = plaqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plaque not found"));

        existing.setCourseCode(plaqueDto.getCourseCode());
        existing.setPeriodo(plaqueDto.getPeriodo());
        existing.setClassName(plaqueDto.getClassName());
        existing.setAprovada(plaqueDto.getAprovada());
        existing.setParaAprovacao(plaqueDto.getParaAprovacao());

        return plaqueRepository.save(existing).toDto();
    }

    @Override
    @Transactional
    public void deletePlaque(UUID id) {
        plaqueRepository.deleteById(id);
    }

    @Override
    public PlaqueDto getPlaqueById(UUID id) {
        return plaqueRepository.findById(id)
                .map(PlaqueModel::toDto)
                .orElseThrow(() -> new RuntimeException("Plaque not found"));
    }

    @Override
    public List<PlaqueDto> getAllPlaques() {
        return plaqueRepository.findAll().stream()
                .map(PlaqueModel::toDto)
                .toList();
    }

    @Override
    public Collection<PlaqueDto> listPlaqueByFilter(String startSemester, String endSemester, String courseCode, String className, boolean approved, boolean toAprove, int campus, String studentName) {
        return null;
    }
}
