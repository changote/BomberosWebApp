package com.bomberos.registro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParteDTO {
    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("isLateral")
    private boolean isLateral;
}
