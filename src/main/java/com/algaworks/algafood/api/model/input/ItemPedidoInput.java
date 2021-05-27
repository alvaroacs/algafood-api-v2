package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

	@Schema(description = "ID do produto", example = "1", required = true)
	@NotNull
	private Long produtoId;
	
	@Schema(description = "Quantidade", example = "1", required = true)
	@NotNull
	@Positive
	private Integer quantidade;
	
	@Schema(description = "Observação", example = "Ao ponto", required = false)
	private String observacao;
}
