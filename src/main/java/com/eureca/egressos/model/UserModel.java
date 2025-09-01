package com.eureca.egressos.model;

import com.eureca.egressos.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "usuarios")
@EqualsAndHashCode
public class UserModel {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    @Column(name = "matricula", length = 255)
    private String enrollment;

    @Column(name = "codigo_de_curso", nullable = false, length = 20)
    private String courseCode;

    @ManyToOne
    @JoinColumn(name = "id_placa", nullable = false)
    private PlaqueModel plaque;

    public UserDto toDto() {
        UserDto dto = new UserDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setEnrollment(this.enrollment);
        dto.setCourseCode(this.courseCode);
        dto.setPlaqueId(this.plaque != null ? this.plaque.getId() : null);
        return dto;
    }
}
