package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInput {

	@Schema(description = "Senha atual", example = "123", required = true)
	@NotBlank
	private String senhaAtual;
	
	@Schema(description = "Nova senha", example = "456", required = true)
	@NotBlank
	private String novaSenha;
}
