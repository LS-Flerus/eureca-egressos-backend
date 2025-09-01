package com.eureca.egressos.service;

import com.eureca.egressos.dto.StudentDto;
import com.eureca.egressos.model.PlaqueModel;
import com.eureca.egressos.model.StudentModel;
import com.eureca.egressos.repository.StudentRepository;
import com.eureca.egressos.service.interfaces.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public StudentDto createStudent(StudentDto studentDto) {
        PlaqueModel plaque = null;
        if (studentDto.getPlaqueId() != null) {
            plaque = PlaqueModel.builder().id(studentDto.getPlaqueId()).build();
        }

        StudentModel student = StudentModel.builder()
                .name(studentDto.getName())
                .courseCode(studentDto.getCourseCode())
                .semester(studentDto.getSemester())
                .photoId(studentDto.getPhotoId())
                .plaque(plaque)
                .build();

        studentRepository.save(student);
        return student.toDto();
    }

    @Override
    @Transactional
    public StudentDto updateStudent(UUID id, StudentDto studentDto) {
        StudentModel existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setName(studentDto.getName());
        existing.setCourseCode(studentDto.getCourseCode());
        existing.setSemester(studentDto.getSemester());
        existing.setPhotoId(studentDto.getPhotoId());

        if (studentDto.getPlaqueId() != null) {
            existing.setPlaque(PlaqueModel.builder().id(studentDto.getPlaqueId()).build());
        } else {
            existing.setPlaque(null);
        }

        return studentRepository.save(existing).toDto();
    }

    @Override
    @Transactional
    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto getStudentById(UUID id) {
        return studentRepository.findById(id)
                .map(StudentModel::toDto)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentModel::toDto)
                .toList();
    }

    @Override
    public List<StudentDto> getStudentsByPlaqueId(UUID plaqueId) {

        return studentRepository.findByPlaque_Id(plaqueId).stream()
                .map(StudentModel::toDto)
                .toList();
    }
}