package com.eureca.egressos.dto.asScao;

import lombok.Data;

@Data
public class EurecaProfileDto {
    private String id;
    private String name;
    private String identityProviderId;
    private Attributes attributes;

    @Data
    public static class Attributes {
        private String code;
        private String type;
        private String email;
    }
}
