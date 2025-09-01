package com.eureca.egressos.controller;

import com.eureca.egressos.controller.documentation.EurecaController;
import com.eureca.egressos.dto.dasScao.ScaoStudentDto;
import com.eureca.egressos.service.interfaces.EurecaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eureca")
public class EurecaControllerImpl implements EurecaController {

    private final EurecaService eurecaService;

    public EurecaControllerImpl(EurecaService eurecaService) {
        this.eurecaService = eurecaService;
    }

    @Override
    @GetMapping("/getAlumniByCourseAndSemester")
    public ResponseEntity<List<ScaoStudentDto>> getAlumniByCourseAndSemester(
            @RequestParam("courseCode") Integer courseCode,
            @RequestParam("startSemester") String startSemester,
            @RequestParam("endSemester") String endSemester,
            @RequestHeader("tokenAS") String tokenAS
    ) {
        List<ScaoStudentDto> alumni = eurecaService.getAlumniStudents(courseCode, startSemester, endSemester, tokenAS);

        return ResponseEntity.ok(alumni);
    }
}
