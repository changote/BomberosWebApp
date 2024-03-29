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
public class LoginDTO {
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;
	
}
