package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioModel extends RepresentationModel<UsuarioModel> {

	@Schema(description = "ID do usuário", example = "1")
	private Long id;
	
	@Schema(description = "Nome do usuário", example = "Thai Gourmet")
	private String nome;
	
	@Schema(description = "E-mail do usuário", example = "thai.gourmet@gmail.com")
	private String email;
}
