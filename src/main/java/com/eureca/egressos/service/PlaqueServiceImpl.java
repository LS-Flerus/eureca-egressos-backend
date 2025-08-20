package com.eureca.egressos.service;

import com.eureca.egressos.dto.PlaqueDto;
import com.eureca.egressos.model.PlaqueModel;
import com.eureca.egressos.repository.PlaqueRepository;
import com.eureca.egressos.repository.StudentRepository;
import com.eureca.egressos.service.interfaces.PlaqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlaqueServiceImpl implements PlaqueService {

    private final PlaqueRepository plaqueRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public PlaqueServiceImpl(PlaqueRepository plaqueRepository, StudentRepository studentRepository) {
        this.plaqueRepository = plaqueRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public PlaqueDto createPlaque(PlaqueDto plaqueDto) {
        PlaqueModel plaque;

        if (plaqueDto.getId() != null) {
            plaque = plaqueRepository.findById(plaqueDto.getId())
                    .orElse(new PlaqueModel());

            plaque.setCourseCode(plaqueDto.getCourseCode());
            plaque.setSemester(plaqueDto.getSemester());
            plaque.setClassName(plaqueDto.getClassName());
            plaque.setApproved(plaqueDto.getApproved());
            plaque.setToApprove(plaqueDto.getToApprove());
            plaque.setCampus(plaqueDto.getCampus());
        } else {
            plaque = PlaqueModel.fromDto(plaqueDto);
        }

        return plaqueRepository.save(plaque).toDto();
    }

    @Override
    @Transactional
    public PlaqueDto updatePlaque(UUID id, PlaqueDto plaqueDto) {
        PlaqueModel existing = plaqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plaque not found"));

        existing.setCourseCode(plaqueDto.getCourseCode());
        existing.setSemester(plaqueDto.getSemester());
        existing.setClassName(plaqueDto.getClassName());
        existing.setApproved(plaqueDto.getApproved());
        existing.setToApprove(plaqueDto.getToApprove());

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
    public Collection<PlaqueDto> listPlaqueByFilter(
            String startSemester,
            String endSemester,
            String courseCode,
            String className,
            Boolean approved,
            Boolean toApprove,
            String campus,
            String studentName
    ) {
        List<PlaqueModel> allPlaques = plaqueRepository.findAll();
        System.out.println(allPlaques);
        final Set<UUID> plaqueIdsFromStudents =
                (studentName != null && !studentName.isBlank())
                        ? studentRepository.findByNameContainingIgnoreCase(studentName)
                        .stream()
                        .map(student -> student.getPlaque().getId())
                        .collect(Collectors.toSet())
                        : Collections.emptySet();

        return allPlaques.stream()
                .filter(plaque -> {
                    double semester = Double.parseDouble(plaque.getSemester());

                    if (approved != null && !plaque.getApproved()) {
                        return false;
                    }

                    if (startSemester != null && semester > Double.parseDouble(startSemester)) {
                        return true;
                    }
                    if (endSemester != null && semester < Double.parseDouble(endSemester)) {
                        return true;
                    }
                    if (courseCode != null && !courseCode.isEmpty() &&
                            Arrays.asList(courseCode.split(",")).contains(plaque.getCourseCode())) {
                        return true;
                    }
                    if (className != null && !className.isEmpty() &&
                            plaque.getClassName().contains(className)) {
                        return true;
                    }

                    if (campus != null && Arrays.asList(campus.split(",")).contains(String.valueOf(plaque.getCampus()))) {
                        return true;
                    }
                    if (!plaqueIdsFromStudents.isEmpty() && !plaqueIdsFromStudents.contains(plaque.getId())) {
                        return false;
                    }

                    return false;
                })
                .map(PlaqueModel::toDto)
                .toList();
    }
}
