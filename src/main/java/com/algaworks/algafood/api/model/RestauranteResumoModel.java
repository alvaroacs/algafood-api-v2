package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteResumoModel extends RepresentationModel<RestauranteResumoModel> {

	@Schema(description = "ID do restaurante", example = "1")
	private Long id;
	
	@Schema(description = "Nome do restaurante", example = "Thai Gourmet")
	private String nome;
	
	@Schema(description = "Taxa frete", example = "1")
	private BigDecimal taxaFrete;
	
	@Schema(implementation = CozinhaModel.class)
	private CozinhaModel cozinha;
}
