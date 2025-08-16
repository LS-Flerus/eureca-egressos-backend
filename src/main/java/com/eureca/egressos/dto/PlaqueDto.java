package com.eureca.egressos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PlaqueDto {
    private UUID id;
    private String courseCode;
    private String periodo;
    private String className;
    private Boolean aprovada;
    private Boolean paraAprovacao;
}
