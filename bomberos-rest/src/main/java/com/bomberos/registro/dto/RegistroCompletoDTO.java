package com.bomberos.registro.dto;

import com.bomberos.registro.entity.RegistroCheck;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroCompletoDTO {

    @JsonProperty("fecha")
    private LocalDateTime fecha;

    @JsonProperty("user")
    private String user;

    @JsonProperty("idUnidad")
    private Long idUnidad;

    @JsonProperty("lateral")
    private RegistroCheckDTO lateral;

    @JsonProperty("motor")
    private RegistroCheckDTO motor;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegistroCheckDTO implements Serializable {

        @JsonProperty("parteList")
        private List<ParteCheckDTO> parteList;

        @JsonProperty("isLateral")
        private boolean isLateral;

        @JsonProperty("observacion")
        private String observacion;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ParteCheckDTO implements Serializable {
            @JsonProperty("idParte")
            private Long idParte;

            @JsonProperty("isOk")
            private boolean isOk;
        }
    }


}
