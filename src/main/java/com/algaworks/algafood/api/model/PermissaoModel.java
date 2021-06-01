package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
public class PermissaoModel extends RepresentationModel<PermissaoModel> {

	@Schema(description = "ID da permissao", example = "1")
	private Long id;

	@Schema(description = "Nome da permissao", example = "EDITAR_COZINHAS")
	private String nome;

	@Schema(description = "Descrição da permissão", example = "Permite editar cozinhas")
	private String descricao;
}
