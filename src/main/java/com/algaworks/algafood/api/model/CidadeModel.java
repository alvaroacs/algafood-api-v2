package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CidadeModel {

	@Schema(description = "ID da cidades", example = "1")
	private Long id;

	@Schema(description = "Nome da Cidade", example = "São Paulo")
	private String nome;

	@Schema(implementation = EstadoModel.class)
	private EstadoModel estado;
}
