package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoModel {

	@Schema(description = "Nome do arquivo", example = "foto.png")
	private String nomeArquivo;
	
	@Schema(description = "Descrição", example = "Filé de Saint Peter")
	private String descricao;
	
	@Schema(description = "Tipo do recurso", example = "image/jpeg")
	private String contentType;
	
	@Schema(description = "Tamanho da imagem", example = "207544")
	private Long tamanho;
}
