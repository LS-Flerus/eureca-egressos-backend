package com.eureca.egressos.service;

import com.eureca.egressos.dto.PhotoDto;
import com.eureca.egressos.dto.PlaqueDto;
import com.eureca.egressos.dto.StudentDto;
import com.eureca.egressos.dto.asScao.EurecaProfileDto;
import com.eureca.egressos.dto.dasScao.ScaoCoursesDto;
import com.eureca.egressos.dto.dasScao.ScaoStudentDto;
import com.eureca.egressos.model.PlaqueModel;
import com.eureca.egressos.model.StudentModel;
import com.eureca.egressos.repository.PhotoRepository;
import com.eureca.egressos.repository.PlaqueRepository;
import com.eureca.egressos.repository.StudentRepository;
import com.eureca.egressos.service.interfaces.EurecaService;
import com.eureca.egressos.service.interfaces.PhotoService;
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
    private final PhotoRepository photoRepository;
    private final StudentService studentService;
    private final EurecaService eurecaService;
    private final PhotoService photoService;
    @Autowired
    public PlaqueServiceImpl(PlaqueRepository plaqueRepository, StudentRepository studentRepository, PhotoRepository photoRepository, StudentService studentService, EurecaService eurecaService, PhotoService photoService) {
        this.plaqueRepository = plaqueRepository;
        this.studentRepository = studentRepository;
        this.photoRepository = photoRepository;
        this.studentService = studentService;
        this.eurecaService = eurecaService;
        this.photoService = photoService;
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
        createPhotoForPlaque(createdPlaque.getId());

        System.out.println("Sucesso: " + createdPlaque.getClassName());
        System.out.println(createdPlaque);
        return createdPlaque.toDto();
    }

    @Override
    @Transactional
    public void creatingAndPraying(String tokenAS) {
        List<ScaoCoursesDto> courses = eurecaService.getActiveCourses();
        PlaqueDto plaque = new PlaqueDto();
        String semester = "2023.2";
        for(ScaoCoursesDto currentCourse : courses) {
            while (!semester.equals("1999.2")) {
                plaque.setCourseCode(String.valueOf(currentCourse.getCodigoDoCurso()));
                plaque.setSemester(semester);
                plaque.setClassName(currentCourse.getDescricao() + " [" + currentCourse.getCodigoDoCurso() + "] - " + semester);
                plaque.setCampus(currentCourse.getCampus());
                plaque.setApproved(true);
                plaque.setToApprove(false);
                try {
                    createPlaque(plaque, tokenAS);
                } catch (Exception e) {
                    System.out.println("Plaque:");
                    System.out.println(plaque);
                    System.out.println(e);
                }
                plaque = new PlaqueDto();
                semester = semesterDecreaser(semester);
            }
            semester = "2023.2";
        }
    }

    private String semesterDecreaser(String semester) {
        String decreasedSemester;
        System.out.println(semester);
        String[] splitedSemester = semester.split("\\.");
        System.out.println(Arrays.toString(splitedSemester));
        int integerYear = Integer.parseInt(splitedSemester[0]);
        int integerSemester =Integer.parseInt(splitedSemester[1]);
        if (integerSemester == 1){
            decreasedSemester = String.valueOf(integerYear - 1) + ".2";
        } else {
            decreasedSemester = String.valueOf(integerYear) + "." + String.valueOf(integerSemester - 1);
        }
        return decreasedSemester;
    }

    private void createPhotoForPlaque(UUID idPlaque) {
        // EurecaProfileDto profile = eurecaService.getEurecaProfile(tokenAS);

        PhotoDto photoDto = new PhotoDto();
        photoDto.setMainPhoto(true);
        photoDto.setPlaqueId(idPlaque);
        photoDto.setPhotoId("");
        photoService.createPhoto(photoDto);
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
                studentDto.setPlaqueId(plaque.getId());

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
    public List<PlaqueDto> getAllPlaquesByCourse(String courseCode) {
        return plaqueRepository.findByCourseCode(courseCode).stream()
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

        final Set<UUID> plaqueIdsFromStudents =
                (studentName != null && !studentName.isBlank())
                        ? studentRepository.findByNameContainingIgnoreCase(studentName)
                        .stream()
                        .map(student -> student.getPlaque().getId())
                        .collect(Collectors.toSet())
                        : Collections.emptySet();

        final List<String> courseCodeList =
                (courseCode != null && !courseCode.isBlank())
                        ? Arrays.asList(courseCode.split(","))
                        : Collections.emptyList();

        final List<String> campusList =
                (campus != null && !campus.isBlank())
                        ? Arrays.asList(campus.split(","))
                        : Collections.emptyList();

        return allPlaques.stream()
                .filter(plaque -> {
                    double semester;
                    try {
                        semester = Double.parseDouble(plaque.getSemester());
                    } catch (Exception e) {
                        return false;
                    }

                    if (!plaqueIdsFromStudents.isEmpty() && !plaqueIdsFromStudents.contains(plaque.getId())) {
                        return false;
                    }

                    if (startSemester != null && !startSemester.isBlank()) {
                        double start = Double.parseDouble(startSemester);
                        if (semester < start) {
                            return false;
                        }
                    }

                    if (endSemester != null && !endSemester.isBlank()) {
                        double end = Double.parseDouble(endSemester);
                        if (semester > end) {
                            return false;
                        }
                    }

                    if (!courseCodeList.isEmpty() && !courseCodeList.contains(plaque.getCourseCode())) {
                        return false;
                    }

                    if (className != null && !className.isBlank()) {
                        if (plaque.getClassName() == null || !plaque.getClassName().contains(className)) {
                            return false;
                        }
                    }

                    if (!campusList.isEmpty() && !campusList.contains(String.valueOf(plaque.getCampus()))) {
                        return false;
                    }

                    return true;
                })
                .sorted(
                        Comparator.comparing(PlaqueModel::getCampus)
                                .thenComparing(PlaqueModel::getCourseCode)
                                .thenComparing(p -> Double.parseDouble(p.getSemester()))
                )
                .map(PlaqueModel::toDto)
                .toList();
    }

}
