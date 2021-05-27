package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {

	@Schema(description = "ID do produto", example = "1")
	private Long produtoId;
	
	@Schema(description = "Nome do produto", example = "Camarão")
	private String produtoNome;
	
	@Schema(description = "Quantidade do item", example = "3")
	private Integer quantidade;
	
	@Schema(description = "Preço unitário do item", example = "1")
	private BigDecimal precoUnitario;
	
	@Schema(description = "Preço total do item do pedido", example = "3")
	private BigDecimal precoTotal;
	
	@Schema(description = "Observação", example = "Ao ponto")
	private String observacao;
}
