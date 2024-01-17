package com.bomberos.registro.dto;

import com.bomberos.registro.entity.Parte;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnidadCompletaDTO {
    @JsonProperty("idUnidad")
    private Long idUnidad;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("parteListPost")
    private List<ParteDTO> parteListPost;

    @JsonProperty("parteList")
    private List<Parte> parteList;
}
