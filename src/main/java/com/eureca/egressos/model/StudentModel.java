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

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "codigo_curso", nullable = false, length = 20)
    private String courseCode;

    @Column(nullable = false, length = 20)
    private String periodo;

    @ManyToOne
    @JoinColumn(name = "id_placa")
    private PlaqueModel plaque;

    @Column(name = "url_foto", length = 50)
    private String photoUrl;

    public StudentDto toDto() {
        StudentDto dto = new StudentDto();
        dto.setId(id);
        dto.setNome(nome);
        dto.setCourseCode(courseCode);
        dto.setPeriodo(periodo);
        dto.setPhotoUrl(photoUrl);
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
                .nome(dto.getNome())
                .courseCode(dto.getCourseCode())
                .periodo(dto.getPeriodo())
                .plaque(plaque)
                .photoUrl(dto.getPhotoUrl())
                .build();
    }
}