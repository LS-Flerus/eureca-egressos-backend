package com.eureca.egressos.service;

import com.eureca.egressos.dto.PlaqueDto;
import com.eureca.egressos.dto.UserDto;
import com.eureca.egressos.model.PlaqueModel;
import com.eureca.egressos.model.UserModel;
import com.eureca.egressos.model.StudentModel;
import com.eureca.egressos.repository.PlaqueRepository;
import com.eureca.egressos.repository.StudentRepository;
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
    private final StudentRepository studentRepository;

    @Autowired
    public PlaqueServiceImpl(PlaqueRepository plaqueRepository, StudentRepository studentRepository) {
        this.plaqueRepository = plaqueRepository;
        this.studentRepository = studentRepository;
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
            boolean approved,
            boolean toApprove,
            int campus,
            String studentName
    ) {
        List<PlaqueModel> allPlaques = plaqueRepository.findAll();

        final Double comparativeStartSemester = startSemester != null ? Double.parseDouble(startSemester) : 9999.9;

        final Double comparativeEndSemester = endSemester != null ? Double.parseDouble(endSemester) : 0.0;

        final Set<UUID> plaqueIdsFromStudents =
                (studentName != null && !studentName.isBlank())
                        ? studentRepository.findByNameContainingIgnoreCase(studentName)
                        .stream()
                        .map(student -> student.getPlaque().getId()) // ou getPlaqueId()
                        .collect(Collectors.toSet())
                        : Collections.emptySet();


        return allPlaques.stream()
                .filter(plaque -> {
                    // Filtro por startSemester
                    if (comparativeStartSemester > Double.parseDouble(plaque.getSemester()) ||
                            comparativeEndSemester < Double.parseDouble(plaque.getSemester())) {
                        return false;
                    }

                    // Filtro por courseCode
                    if (courseCode != null && !courseCode.isEmpty() &&
                            !courseCode.equals(plaque.getCourseCode())) {
                        return false;
                    }

                    // Filtro por className
                    if (className != null && !className.isEmpty() &&
                            !plaque.getClassName().contains(className)) {
                        return false;
                    }

                    // Filtro por approved
                    if (approved == plaque.getApproved()) {
                        return false;
                    }

                    // Filtro por toApprove
                    if (toApprove == plaque.getToApprove()) {
                        return false;
                    }

                    // Filtro por campus
                    if (campus > 0 && plaque.getCampus() != campus) {
                        return false;
                    }

                    // Filtro por studentName (se fornecido)
                    if (!plaqueIdsFromStudents.isEmpty() && !plaqueIdsFromStudents.contains(plaque.getId())) {
                        return false;
                    }

                    return true;
                })
                .map(PlaqueModel::toDto)
                .toList();
    }
}
