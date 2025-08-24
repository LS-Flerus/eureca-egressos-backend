package com.eureca.egressos.service;

import com.eureca.egressos.dto.PlaqueDto;
import com.eureca.egressos.dto.StudentDto;
import com.eureca.egressos.dto.asScao.EurecaProfileDto;
import com.eureca.egressos.dto.dasScao.ScaoStudentDto;
import com.eureca.egressos.model.PlaqueModel;
import com.eureca.egressos.model.StudentModel;
import com.eureca.egressos.repository.PlaqueRepository;
import com.eureca.egressos.repository.StudentRepository;
import com.eureca.egressos.service.interfaces.EurecaService;
import com.eureca.egressos.service.interfaces.PlaqueService;
import com.eureca.egressos.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlaqueServiceImpl implements PlaqueService {

    private final PlaqueRepository plaqueRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final EurecaService eurecaService;

    @Autowired
    public PlaqueServiceImpl(PlaqueRepository plaqueRepository, StudentRepository studentRepository, StudentService studentService, EurecaService eurecaService) {
        this.plaqueRepository = plaqueRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.eurecaService = eurecaService;
    }

    @Override
    @Transactional
    public PlaqueDto createPlaque(PlaqueDto plaqueDto, String tokenAS) {
        PlaqueModel plaque;

        List<PlaqueDto> plaquesInDb = this.getAllPlaques();

        Set<String> existingPlaques = plaquesInDb.stream()
                .map(p -> p.getSemester() + "|" + p.getCourseCode())
                .collect(Collectors.toSet());

        String newPlaqueKey = plaqueDto.getSemester() + "|" + plaqueDto.getCourseCode();

        if (existingPlaques.contains(newPlaqueKey)) {
            throw new IllegalArgumentException(
                    String.format("Já existe uma placa para o curso %s no semestre %s.",
                            plaqueDto.getCourseCode(), plaqueDto.getSemester())
            );
        }

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

        PlaqueModel createdPlaque = plaqueRepository.save(plaque);
        createStudentsForPlaque(createdPlaque, tokenAS);

        return createdPlaque.toDto();
    }
    private void createStudentsForPlaque(PlaqueModel plaque, String tokenAS) {
        // EurecaProfileDto profile = eurecaService.getEurecaProfile(tokenAS);

        List<ScaoStudentDto> studentsFromScao = eurecaService.getAlumniStudents(
                Integer.parseInt(plaque.getCourseCode()),
                plaque.getSemester(),
                plaque.getSemester(),
                tokenAS);

        List<StudentDto> studentsInDb = studentService.getAllStudents();

        Set<String> existingNames = studentsInDb.stream()
                .map(StudentDto::getName)
                .collect(Collectors.toSet());

        for(ScaoStudentDto currentStudent : studentsFromScao) {
            if (!existingNames.contains(currentStudent.getName())) {
                StudentDto studentDto = new StudentDto();

                studentDto.setName(currentStudent.getName());
                studentDto.setCourseCode(String.valueOf(currentStudent.getCourseCode()));
                studentDto.setSemester(currentStudent.getInactivityTerm());
                studentDto.setPhotoId("");

                studentService.createStudent(studentDto);
                existingNames.add(currentStudent.getName());
            }
        }
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
        PlaqueDto plaque = plaqueRepository.findById(id)
                .map(PlaqueModel::toDto)
                .orElseThrow(() -> new RuntimeException("Plaque not found"));

        return plaque;
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
