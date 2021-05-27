package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {

	@Schema(description = "ID do restaurante", example = "1")
	private Long id;
	
	@Schema(description = "Nome do restaurante", example = "Thai Gourmet")
	private String nome;
	
	@Schema(description = "Preço da taxa do frete", example = "1")
	private BigDecimal taxaFrete;
	
	@Schema(description = "Cozinha", implementation = CozinhaModel.class)
	private CozinhaModel cozinha;
	
	@Schema(description = "Se o restaurante está ativo", example = "true")
	private Boolean ativo;
	
	@Schema(description = "Se o restaurante está aberto", example = "true")
	private Boolean aberto;
	
	@Schema(description = "Endereço do restaurante", implementation = EnderecoModel.class)
	private EnderecoModel endereco;
}
