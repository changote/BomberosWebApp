package com.bomberos.registro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	@JsonProperty("username")
	private String username;
	
	@JsonProperty("nombre")
	private String nombre;
	
	@JsonProperty("bloqueado")
	private boolean bloqueado;

	@JsonProperty("uid")
	private Long userId;
}