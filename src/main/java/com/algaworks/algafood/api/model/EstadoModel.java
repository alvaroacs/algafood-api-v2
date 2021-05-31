package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoModel extends RepresentationModel<EstadoModel> {

	@Schema(description = "ID do estado", example = "1")
	public Long id;
	
	@Schema(description = "Nome do estado", example = "São Paulo")
	public String nome;
}
