package com.eureca.egressos.dto.dasScao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScaoCoursesDto {
    @JsonProperty("codigo_do_curso")
    private Long codigoDoCurso;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("status")
    private String status;

    @JsonProperty("grau_do_curso")
    private String grauDoCurso;

    @JsonProperty("codigo_do_setor")
    private Long codigoDoSetor;

    @JsonProperty("nome_do_setor")
    private String nomeDoSetor;

    @JsonProperty("campus")
    private Integer campus;

    @JsonProperty("nome_do_campus")
    private String nomeDoCampus;

    @JsonProperty("turno")
    private String turno;

    @JsonProperty("periodo_de_inicio")
    private String periodoDeInicio;

    @JsonProperty("data_de_funcionamento")
    private String dataDeFuncionamento;

    @JsonProperty("codigo_inep")
    private Integer codigoInep;

    @JsonProperty("modalidade_academica")
    private String modalidadeAcademica;

    @JsonProperty("curriculo_atual")
    private Integer curriculoAtual;

    @JsonProperty("area_de_retencao")
    private Integer areaDeRetencao;

    @JsonProperty("ciclo_enade")
    private Integer cicloEnade;
}
