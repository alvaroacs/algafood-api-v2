package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {

	@Schema(description = "Nome", example = "São Paulo", required = true)
	@NotBlank
	private String nome;
}
