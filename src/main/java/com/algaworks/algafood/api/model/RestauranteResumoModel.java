package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResumoModel {

	@Schema(description = "ID do restaurante", example = "1")
	private Long id;
	
	@Schema(description = "Nome do restaurante", example = "Thai Gourmet")
	private String nome;
}
