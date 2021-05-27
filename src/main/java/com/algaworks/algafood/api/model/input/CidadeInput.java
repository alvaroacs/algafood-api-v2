package com.algaworks.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {

	@Schema(description = "Nome da cidade", example = "São Paulo", required = true)
	@NotBlank
	private String nome;
	
	@Schema(implementation = EstadoIdInput.class)
	@Valid
	@NotNull
	private EstadoIdInput estado;
}
