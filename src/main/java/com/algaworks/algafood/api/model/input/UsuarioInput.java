package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@Schema(description = "Nome", example = "Thai Gourmet", required = true)
	@NotBlank
	private String nome;
	
	@Schema(description = "Email", example = "thaigourmet@gmail.com", required = true)
	@Email
	@NotBlank
	private String email;
}
