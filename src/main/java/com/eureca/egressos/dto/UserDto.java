package com.eureca.egressos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private UUID id;
    private String name;
    private String enrollment;
    private String courseCode;
    private UUID plaqueId;
}
