package com.eureca.egressos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PlaqueSessionDto {
    private UUID id;
    private UUID plaqueId;
    private String nome;
    private String conteudo;
    private Boolean lista;
}