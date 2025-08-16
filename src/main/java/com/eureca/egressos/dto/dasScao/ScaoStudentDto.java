package com.eureca.egressos.dto.dasScao;

import com.eureca.egressos.model.dasScao.ScaoStudentModel;
import com.eureca.egressos.util.EurecaJsonProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ScaoStudentDto {

    @JsonProperty(EurecaJsonProperties.MATRICULA_ALUNO)
    private String registration;

    @JsonProperty(EurecaJsonProperties.NOME)
    private String name;

    @JsonProperty(EurecaJsonProperties.COD_CURSO)
    private Integer courseCode;

    @JsonProperty(EurecaJsonProperties.NOME_CURSO)
    private String courseName;

    @JsonProperty(EurecaJsonProperties.TURNO_CURSO)
    private String courseSchedule;

    @JsonProperty(EurecaJsonProperties.COD_CURRICULO)
    private Integer curriculumCode;

    @JsonProperty(EurecaJsonProperties.CAMPUS)
    private Integer campus;

    @JsonProperty(EurecaJsonProperties.CAMPUS_NOME)
    private String campusName;

    @JsonProperty(EurecaJsonProperties.COD_SETOR)
    private Integer sector;

    @JsonProperty(EurecaJsonProperties.SECTOR_NAME)
    private String sectorName;

    @JsonProperty(EurecaJsonProperties.ESTADO_CIVIL)
    private String maritalStatus;

    @JsonProperty(EurecaJsonProperties.ENDERECO)
    private String address;

    @JsonProperty(EurecaJsonProperties.SEXO)
    private String gender;

    @JsonProperty(EurecaJsonProperties.DATA_NASCIMENTO)
    private String birthDate;

    @JsonProperty(EurecaJsonProperties.CPF)
    private String citizenId;

    @JsonProperty(EurecaJsonProperties.CEP)
    private String cep;

    @JsonProperty(EurecaJsonProperties.TELEFONE)
    private String phone;

    @JsonProperty(EurecaJsonProperties.SITUACAO)
    private String status;

    @JsonProperty(EurecaJsonProperties.FORMA_EVASAO)
    private String inactivityReason;

    @JsonProperty(EurecaJsonProperties.PERIODO_EVASAO)
    private String inactivityTerm;

    @JsonProperty(EurecaJsonProperties.FORMA_INGRESSO)
    private String admissionType;

    @JsonProperty(EurecaJsonProperties.PERIODO_INGRESSO)
    private String admissionTerm;

    @JsonProperty(EurecaJsonProperties.EMAIL)
    private String email;

    @JsonProperty(EurecaJsonProperties.NACIONALIDADE)
    private String nationality;

    @JsonProperty(EurecaJsonProperties.LOCAL_DE_NASCIMENTO)
    private String placeOfBirth;

    @JsonProperty(EurecaJsonProperties.NATURALIDADE)
    private String federativeUnit;

    @JsonProperty(EurecaJsonProperties.COR)
    private String race;

    @JsonProperty(EurecaJsonProperties.DEFICIENCIAS)
    private List<String> disabilities;


    @JsonProperty(EurecaJsonProperties.ANO_CONCLUSAO_ENSINO_MEDIO)
    private Integer secondarySchoolGraduationYear;

    @JsonProperty(EurecaJsonProperties.TIPO_ENSINO_MEDIO)
    private String secondarySchoolType;

    @JsonProperty(EurecaJsonProperties.TIPO_RESERVA_VAGAS)
    private String affirmativePolicy;

    @JsonProperty(EurecaJsonProperties.CREDITOS_CRA)
    private Integer gpaCredits;

    @JsonProperty(EurecaJsonProperties.NOTAS_ACUMULADAS)
    private Double accumulatedGrade;

    @JsonProperty(EurecaJsonProperties.PERIODOS_COMPLETADOS)
    private Integer completedTerms;

    @JsonProperty(EurecaJsonProperties.CREDITOS_TENTADOS)
    private Integer attemptedCredits;

    @JsonProperty(EurecaJsonProperties.CREDITOS_COMPLETADOS)
    private Integer successfulCredits;

    @JsonProperty(EurecaJsonProperties.CREDITOS_ISENTOS)
    private Integer exemptedCredits;

    @JsonProperty(EurecaJsonProperties.CREDITOS_FALHADOS)
    private Integer failedCredits;

    @JsonProperty(EurecaJsonProperties.CREDITOS_SUSPENSOS)
    private Integer suspendedCredits;

    @JsonProperty(EurecaJsonProperties.CREDITOS_EM_ANDAMENTO)
    private Integer ongoingCredits;

    @JsonProperty(EurecaJsonProperties.VELOCIDADE_MEDIA)
    private Double averageSpeed;

    @JsonProperty(EurecaJsonProperties.TAXA_DE_SUCESSO)
    private Double successfulRate;

    @JsonProperty(EurecaJsonProperties.PRAC_ATUALIZADO)
    private String updatedPrac;

    @JsonProperty(EurecaJsonProperties.PRAC_ATUALIZADO_EM)
    private String updatedInPrac;

    @JsonProperty(EurecaJsonProperties.PRAC_COR)
    private String skinColorPrac;

    @JsonProperty(EurecaJsonProperties.PRAC_QUILOMBOA)
    private String quilombolaPrac;

    @JsonProperty(EurecaJsonProperties.PRAC_INDIGENA_ALDEADO)
    private String indigenousPrac;

    @JsonProperty(EurecaJsonProperties.PRAC_RENDA_PER_CAPITA_ATE)
    private Float perCapitaIncomePrac;

    @JsonProperty(EurecaJsonProperties.PRAC_DEFICIENTE)
    private String disabledPrac;

    @JsonProperty(EurecaJsonProperties.PRAC_DEFICIENCIAS)
    private List<String> disabilitiesPRAC;

    @JsonProperty(EurecaJsonProperties.PRAC_DESLOCOU_MUDOU)
    private String movedPrac;

    @JsonProperty(EurecaJsonProperties.UFPB)
    private Integer ufpb;

    public static ScaoStudentDto fromModel(ScaoStudentModel model) {
        if (model == null) {
            return null;
        }

        ScaoStudentDto dto = new ScaoStudentDto();

        dto.setRegistration(model.getMatriculaDoEstudante());
        dto.setName(model.getNome());
        dto.setCourseCode(model.getCodCurso());
        dto.setCourseName(model.getNomeCurso());
        dto.setCourseSchedule(model.getTurnoCurso());
        dto.setCurriculumCode(model.getCodCurriculo());
        dto.setCampus(model.getCampus());
        dto.setCampusName(model.getCampusNome());
        dto.setSector(model.getCodSetor());
        dto.setSectorName(model.getNomeSetor());
        dto.setMaritalStatus(model.getEstadoCivil());
        dto.setAddress(model.getEndereco());
        dto.setGender(model.getSexo());
        dto.setBirthDate(model.getDataNascimento());
        dto.setCitizenId(model.getCpf());
        dto.setCep(model.getCep());
        dto.setPhone(model.getTelefone());
        dto.setStatus(model.getSituacao());
        dto.setInactivityReason(model.getFormaEvasao());
        dto.setInactivityTerm(model.getPeriodoEvasao());
        dto.setAdmissionType(model.getFormaIngresso());
        dto.setAdmissionTerm(model.getPeriodoIngresso());
        dto.setEmail(model.getEmail());
        dto.setNationality(model.getNacionalidade());
        dto.setPlaceOfBirth(model.getLocalDeNascimento());
        dto.setFederativeUnit(model.getNaturalidade());
        dto.setRace(model.getCor());
        dto.setDisabilities(model.getDeficiencias());

        dto.setSecondarySchoolGraduationYear(model.getAnoConclusaoEnsinoMedio());
        dto.setSecondarySchoolType(model.getTipoEnsinoMedio());
        dto.setAffirmativePolicy(model.getPoliticaAfirmativa());
        dto.setGpaCredits(model.getCreditosCra());
        dto.setAccumulatedGrade(model.getNotasAcumuladas());
        dto.setCompletedTerms(model.getPeriodosCompletados());
        dto.setAttemptedCredits(model.getCreditosTentados());
        dto.setSuccessfulCredits(model.getCreditosCompletados());
        dto.setExemptedCredits(model.getCreditosIsentos());
        dto.setFailedCredits(model.getCreditosFalhados());
        dto.setSuspendedCredits(model.getCreditosSuspensos());
        dto.setOngoingCredits(model.getCreditosEmAndamento());
        dto.setAverageSpeed(model.getVelocidadeMedia());
        dto.setSuccessfulRate(model.getTaxaDeSucesso());
        dto.setUpdatedPrac(model.getPracAtualizado());
        dto.setUpdatedInPrac(model.getPracAtualizadoEm());
        dto.setSkinColorPrac(model.getPracCor());
        dto.setQuilombolaPrac(model.getPracQuilombola());
        dto.setIndigenousPrac(model.getPracIndigenaAldeado());
        dto.setPerCapitaIncomePrac(model.getPracRendaPerCapitaAte());
        dto.setDisabledPrac(model.getPracDeficiente());
        dto.setDisabilitiesPRAC(model.getPracDeficiencias());
        dto.setMovedPrac(model.getPracDeslocouMudou());
        dto.setUfpb(model.getUfpb());

        return dto;
    }
}

