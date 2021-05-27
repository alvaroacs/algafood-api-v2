package com.algaworks.algafood.api.model.input;

import com.sun.istack.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteIdInput {

	@Schema(description = "ID do restaurante", example = "1", required = true)
	@NotNull
	private Long id;
}
