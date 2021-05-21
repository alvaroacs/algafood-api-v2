package com.algaworks.algafoodapi.api.model.input;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

	@NotNull
	private Long produtoId;
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	private String observacao;
}
