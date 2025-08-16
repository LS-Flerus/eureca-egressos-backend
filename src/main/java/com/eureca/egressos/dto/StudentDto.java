package com.eureca.egressos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StudentDto {
    private UUID id;
    private String nome;
    private String courseCode;
    private String periodo;
    private UUID plaqueId;
    private String photoUrl;
}
