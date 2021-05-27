package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoModel {

	@Schema(description = "ID da permissao", example = "1")
	private Long id;

	@Schema(description = "Nome da permissao", example = "EDITAR_COZINHAS")
	private String nome;

	@Schema(description = "Descrição da permissão", example = "Permite editar cozinhas")
	private String descricao;
}
