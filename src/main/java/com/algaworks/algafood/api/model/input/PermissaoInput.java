package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoInput {

	@Schema(description = "Nome", example = "EDITAR_COZINHAS", required = true)
	@NotBlank
	private String nome;
	
	@Schema(description = "Descrição", example = "Permite editar cozinhas", required = true)
	@NotBlank
	private String descricao;

	public void setNome(String nome) {
		this.nome = nome.replaceAll(" ", "_").toUpperCase();
	}
	
}
