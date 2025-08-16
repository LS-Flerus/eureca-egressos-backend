package com.eureca.egressos.model;

import com.eureca.egressos.dto.StudentDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alunos")
public class StudentModel {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    private String name;

    @Column(name = "codigo_curso", nullable = false, length = 20)
    private String courseCode;

    @Column(name = "periodo", nullable = false, length = 20)
    private String semester;

    @ManyToOne
    @JoinColumn(name = "id_placa")
    private PlaqueModel plaque;

    @Column(name = "id_foto", length = 50)
    private String photoId;

    public StudentDto toDto() {
        StudentDto dto = new StudentDto();
        dto.setId(id);
        dto.setName(name);
        dto.setCourseCode(courseCode);
        dto.setSemester(semester);
        dto.setPhotoId(photoId);
        if (plaque != null) {
            dto.setPlaqueId(plaque.getId());
        }
        return dto;
    }

    public static StudentModel fromDto(StudentDto dto) {
        PlaqueModel plaque = null;
        if (dto.getPlaqueId() != null) {
            plaque = PlaqueModel.builder().id(dto.getPlaqueId()).build();
        }

        return StudentModel.builder()
                .id(dto.getId())
                .name(dto.getName())
                .courseCode(dto.getCourseCode())
                .semester(dto.getSemester())
                .plaque(plaque)
                .photoId(dto.getPhotoId())
                .build();
    }
}