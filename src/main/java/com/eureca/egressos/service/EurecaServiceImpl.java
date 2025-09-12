package com.eureca.egressos.service;

import com.eureca.egressos.dto.asScao.EurecaProfileDto;
import com.eureca.egressos.dto.dasScao.ScaoCoursesDto;
import com.eureca.egressos.dto.dasScao.ScaoStudentDto;
import com.eureca.egressos.model.dasScao.ScaoStudentModel;
import com.eureca.egressos.service.interfaces.EurecaService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.eureca.egressos.util.Constants.*;
@Service
public class EurecaServiceImpl implements EurecaService {

    private final String baseUrl = dasUrl;
    private final String baseAsUrl = asUrl;
    private final RestTemplate restTemplate;

    public EurecaServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    public List<ScaoStudentDto> getAlumniStudents(Integer courseCode, String startSemester, String endSemester, String tokenAS) {
        String url = baseUrl +
                "/estudantes?situacao-do-estudante=" + nameAlumniStatus +
                "&periodo-de-evasao-de=" + startSemester +
                "&periodo-de-evasao-ate=" + endSemester +
                "&curso=" + courseCode;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("token-de-autenticacao", tokenAS);
            headers.set("Content-Type", "application/json");

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<List<ScaoStudentModel>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {}
            );

            List<ScaoStudentModel> students = response.getBody();
            if (students == null) {
                return List.of();
            }

            return students.stream().map(ScaoStudentDto::fromModel).toList();

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                return List.of();
            } else {
                throw e;
            }
        }
    }

    public List<ScaoCoursesDto> getActiveCourses() {
        String url = baseUrl + "/cursos?status=ATIVOS";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<List<ScaoCoursesDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {}
            );

            List<ScaoCoursesDto> courses = response.getBody();
            if (courses == null) {
                return List.of();
            }

            return courses;

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                return List.of();
            } else {
                throw e;
            }
        }
    }

    public EurecaProfileDto getEurecaProfile(String tokenAS) {
        String url = baseAsUrl+"/profile";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("token-de-autenticacao", tokenAS);
            headers.set("Content-Type", "application/json");

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<EurecaProfileDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {}
            );

            EurecaProfileDto profile = response.getBody();

            return profile;

        } catch (HttpClientErrorException e) {
            throw e;
        }
    }
}