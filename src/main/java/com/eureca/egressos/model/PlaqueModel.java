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
    private String periodo;

    @Column(name = "nome_turma", length = 30)
    private String className;

    private Boolean aprovada;

    private Boolean paraAprovacao;

    public PlaqueDto toDto() {
        PlaqueDto dto = new PlaqueDto();
        dto.setId(id);
        dto.setCourseCode(courseCode);
        dto.setPeriodo(periodo);
        dto.setClassName(className);
        dto.setAprovada(aprovada);
        dto.setParaAprovacao(paraAprovacao);

        return dto;
    }

    public static PlaqueModel fromDto(PlaqueDto dto) {
        return PlaqueModel.builder()
                .id(dto.getId())
                .courseCode(dto.getCourseCode())
                .periodo(dto.getPeriodo())
                .className(dto.getClassName())
                .aprovada(dto.getAprovada())
                .paraAprovacao(dto.getParaAprovacao())
                .build();
    }
}

