package com.eureca.egressos.model.dasScao;

import com.eureca.egressos.util.EurecaJsonProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StudentModel {

    @JsonProperty(EurecaJsonProperties.MATRICULA_ALUNO)
    private String matriculaDoEstudante;

    @JsonProperty(EurecaJsonProperties.NOME)
    private String nome;

    @JsonProperty(EurecaJsonProperties.COD_CURSO)
    private Integer codCurso;

    @JsonProperty(EurecaJsonProperties.NOME_CURSO)
    private String nomeCurso;

    @JsonProperty(EurecaJsonProperties.TURNO_CURSO)
    private String turnoCurso;

    @JsonProperty(EurecaJsonProperties.COD_CURRICULO)
    private Integer codCurriculo;

    @JsonProperty(EurecaJsonProperties.CAMPUS)
    private Integer campus;

    @JsonProperty(EurecaJsonProperties.CAMPUS_NOME)
    private String campusNome;

    @JsonProperty(EurecaJsonProperties.COD_SETOR)
    private Integer codSetor;

    @JsonProperty(EurecaJsonProperties.SECTOR_NAME)
    private String nomeSetor;

    @JsonProperty(EurecaJsonProperties.ESTADO_CIVIL)
    private String estadoCivil;

    @JsonProperty(EurecaJsonProperties.ENDERECO)
    private String endereco;

    @JsonProperty(EurecaJsonProperties.SEXO)
    private String sexo;

    @JsonProperty(EurecaJsonProperties.DATA_NASCIMENTO)
    private String dataNascimento;

    @JsonProperty(EurecaJsonProperties.CPF)
    private String cpf;

    @JsonProperty(EurecaJsonProperties.CEP)
    private String cep;

    @JsonProperty(EurecaJsonProperties.TELEFONE)
    private String telefone;

    @JsonProperty(EurecaJsonProperties.SITUACAO)
    private String situacao;

    @JsonProperty(EurecaJsonProperties.FORMA_EVASAO)
    private String formaEvasao;

    @JsonProperty(EurecaJsonProperties.PERIODO_EVASAO)
    private String periodoEvasao;

    @JsonProperty(EurecaJsonProperties.FORMA_INGRESSO)
    private String formaIngresso;

    @JsonProperty(EurecaJsonProperties.PERIODO_INGRESSO)
    private String periodoIngresso;

    @JsonProperty(EurecaJsonProperties.EMAIL)
    private String email;

    @JsonProperty(EurecaJsonProperties.NACIONALIDADE)
    private String nacionalidade;

    @JsonProperty(EurecaJsonProperties.LOCAL_DE_NASCIMENTO)
    private String localDeNascimento;

    @JsonProperty(EurecaJsonProperties.NATURALIDADE)
    private String naturalidade;

    @JsonProperty(EurecaJsonProperties.COR)
    private String cor;

    @JsonProperty(EurecaJsonProperties.DEFICIENCIAS)
    private List<String> deficiencias;

    @JsonProperty(EurecaJsonProperties.ANO_CONCLUSAO_ENSINO_MEDIO)
    private Integer anoConclusaoEnsinoMedio;

    @JsonProperty(EurecaJsonProperties.TIPO_ENSINO_MEDIO)
    private String tipoEnsinoMedio;

    @JsonProperty(EurecaJsonProperties.TIPO_RESERVA_VAGAS)
    private String politicaAfirmativa;

    @JsonProperty(EurecaJsonProperties.CREDITOS_CRA)
    private Integer creditosCra;

    @JsonProperty(EurecaJsonProperties.NOTAS_ACUMULADAS)
    private Double notasAcumuladas;

    @JsonProperty(EurecaJsonProperties.PERIODOS_COMPLETADOS)
    private Integer periodosCompletados;

    @JsonProperty(EurecaJsonProperties.CREDITOS_TENTADOS)
    private Integer creditosTentados;

    @JsonProperty(EurecaJsonProperties.CREDITOS_COMPLETADOS)
    private Integer creditosCompletados;

    @JsonProperty(EurecaJsonProperties.CREDITOS_ISENTOS)
    private Integer creditosIsentos;

    @JsonProperty(EurecaJsonProperties.CREDITOS_FALHADOS)
    private Integer creditosFalhados;

    @JsonProperty(EurecaJsonProperties.CREDITOS_SUSPENSOS)
    private Integer creditosSuspensos;

    @JsonProperty(EurecaJsonProperties.CREDITOS_EM_ANDAMENTO)
    private Integer creditosEmAndamento;

    @JsonProperty(EurecaJsonProperties.VELOCIDADE_MEDIA)
    private Double velocidadeMedia;

    @JsonProperty(EurecaJsonProperties.TAXA_DE_SUCESSO)
    private Double taxaDeSucesso;

    @JsonProperty(EurecaJsonProperties.PRAC_ATUALIZADO)
    private String pracAtualizado;

    @JsonProperty(EurecaJsonProperties.PRAC_ATUALIZADO_EM)
    private String pracAtualizadoEm;

    @JsonProperty(EurecaJsonProperties.PRAC_COR)
    private String pracCor;

    @JsonProperty(EurecaJsonProperties.PRAC_QUILOMBOA)
    private String pracQuilombola;

    @JsonProperty(EurecaJsonProperties.PRAC_INDIGENA_ALDEADO)
    private String pracIndigenaAldeado;

    @JsonProperty(EurecaJsonProperties.PRAC_RENDA_PER_CAPITA_ATE)
    private Float pracRendaPerCapitaAte;

    @JsonProperty(EurecaJsonProperties.PRAC_DEFICIENTE)
    private String pracDeficiente;

    @JsonProperty(EurecaJsonProperties.PRAC_DEFICIENCIAS)
    private List<String> pracDeficiencias;

    @JsonProperty(EurecaJsonProperties.PRAC_DESLOCOU_MUDOU)
    private String pracDeslocouMudou;

    @JsonProperty(EurecaJsonProperties.UFPB)
    private Integer ufpb;

    public String getSexo() { return sexo; }
    public String getCor() { return cor; }
    public List<String> getDeficiencias() { return deficiencias; }
    public String getPoliticaAfirmativa() { return politicaAfirmativa; }

}
