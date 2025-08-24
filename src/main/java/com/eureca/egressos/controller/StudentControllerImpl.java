package com.eureca.egressos.controller;

import com.eureca.egressos.controller.documentation.StudentController;
import com.eureca.egressos.dto.StudentDto;
import com.eureca.egressos.service.interfaces.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentControllerImpl implements StudentController {

    private final StudentService studentService;

    public StudentControllerImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.createStudent(studentDto));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<StudentDto> updateStudent(@RequestParam UUID id, @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDto));
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteStudent(@RequestParam UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/getById")
    public ResponseEntity<StudentDto> getStudentById(@RequestParam UUID id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @Override
    @GetMapping("/getAllByPlaqueId")
    public ResponseEntity<List<StudentDto>> getAllStudentsByPlaqueId(@RequestParam UUID id) {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}