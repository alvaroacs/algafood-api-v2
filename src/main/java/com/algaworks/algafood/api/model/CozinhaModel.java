package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {

	@Schema(description = "ID da cozinha", example = "1")
	private Long id;
	
	@Schema(description = "Nome da cozinha", example = "Árabe")
	private String nome;
}
