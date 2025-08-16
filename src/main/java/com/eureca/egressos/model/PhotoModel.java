package com.eureca.egressos.model;

import com.eureca.egressos.dto.PhotoDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fotos")
public class PhotoModel {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "id_foto", nullable = false, length = 50)
    private String photoId;

    @ManyToOne
    @JoinColumn(name = "id_placa", nullable = false)
    private PlaqueModel plaque;

    public PhotoDto toDto() {
        PhotoDto dto = new PhotoDto();
        dto.setId(id);
        dto.setPhotoId(photoId);
        if (plaque != null) {
            dto.setPlaqueId(plaque.getId());
        }
        return dto;
    }

    public static PhotoModel fromDto(PhotoDto dto) {
        PlaqueModel plaque = null;
        if (dto.getPlaqueId() != null) {
            plaque = PlaqueModel.builder().id(dto.getPlaqueId()).build();
        }

        return PhotoModel.builder()
                .id(dto.getId())
                .plaque(plaque)
                .photoId(dto.getPhotoId())
                .build();
    }
}
