package com.eureca.egressos.service.interfaces;

import com.eureca.egressos.dto.asScao.EurecaProfileDto;
import com.eureca.egressos.dto.dasScao.ScaoStudentDto;

import java.util.List;

public interface EurecaService {
    List<ScaoStudentDto> getAlumniStudents(Integer courseCode, String startSemester, String endSemester, String tokenAS);
    EurecaProfileDto getEurecaProfile(String token);
}
