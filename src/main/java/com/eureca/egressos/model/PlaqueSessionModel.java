package com.eureca.egressos.model;

import com.eureca.egressos.dto.PlaqueSessionDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "placa_sessoes")
@EqualsAndHashCode
public class PlaqueSessionModel {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_placa")
    private PlaqueModel plaque;

    @Column(nullable = false, length = 20)
    private String nome;

    @Column(nullable = false, length = 255)
    private String conteudo;

    private Boolean lista;

    public PlaqueSessionDto toDto() {
        PlaqueSessionDto dto = new PlaqueSessionDto();
        dto.setId(this.id);
        dto.setPlaqueId(this.plaque != null ? this.plaque.getId() : null);
        dto.setNome(this.nome);
        dto.setConteudo(this.conteudo);
        dto.setLista(this.lista);
        return dto;
    }

    public static PlaqueSessionModel fromDto(PlaqueSessionDto dto) {
        PlaqueModel plaque = null;
        if (dto.getPlaqueId() != null) {
            plaque = PlaqueModel.builder().id(dto.getPlaqueId()).build();
        }

        return PlaqueSessionModel.builder()
                .id(dto.getId())
                .plaque(plaque)
                .nome(dto.getNome())
                .conteudo(dto.getConteudo())
                .lista(dto.getLista())
                .build();
    }
}
