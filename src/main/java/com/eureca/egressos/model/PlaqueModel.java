package com.eureca.egressos.model;

import com.eureca.egressos.dto.PlaqueDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "placas")
public class PlaqueModel {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "codigo_curso", nullable = false, length = 20)
    private String courseCode;

    @Column(nullable = false, length = 20)
    private String semester;

    @Column(name = "nome_turma", length = 30)
    private String className;

    @Column(name = "campus", length = 30)
    private int campus;

    @Column(name = "aprovada")
    private Boolean approved;

    @Column(name = "para_aprovacao")
    private Boolean toApprove;

    public PlaqueDto toDto() {
        PlaqueDto dto = new PlaqueDto();
        dto.setId(id);
        dto.setCourseCode(courseCode);
        dto.setSemester(semester);
        dto.setClassName(className);
        dto.setCampus(campus);
        dto.setApproved(approved);
        dto.setToApprove(toApprove);

        return dto;
    }

    public static PlaqueModel fromDto(PlaqueDto dto) {
        return PlaqueModel.builder()
                .id(dto.getId())
                .courseCode(dto.getCourseCode())
                .semester(dto.getSemester())
                .className(dto.getClassName())
                .campus(dto.getCampus())
                .approved(dto.getApproved())
                .toApprove(dto.getToApprove())
                .build();
    }
}

