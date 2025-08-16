package com.eureca.egressos.service.interfaces;

import com.eureca.egressos.dto.StudentDto;
import java.util.List;
import java.util.UUID;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto updateStudent(UUID id, StudentDto studentDto);
    void deleteStudent(UUID id);
    StudentDto getStudentById(UUID id);
    List<StudentDto> getAllStudents();
    List<StudentDto> getStudentsByPlaqueId(UUID plaqueId);
}