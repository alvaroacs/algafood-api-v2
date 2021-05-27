package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInput {

	@Schema(description = "ID do estado", example = "1", required = true)
	@NotNull
	private Long id;
}
