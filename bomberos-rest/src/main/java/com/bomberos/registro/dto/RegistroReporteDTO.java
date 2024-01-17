package com.bomberos.registro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroReporteDTO {
    @JsonProperty("fecha")
    private String fecha;

    @JsonProperty("user")
    private String user;

    @JsonProperty("lateralOk")
    private boolean lateralOk;

    @JsonProperty("motorOk")
    private boolean motorOk;
}
