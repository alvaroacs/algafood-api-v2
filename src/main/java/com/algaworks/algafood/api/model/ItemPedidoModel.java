package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "itens")
@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel> {

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
