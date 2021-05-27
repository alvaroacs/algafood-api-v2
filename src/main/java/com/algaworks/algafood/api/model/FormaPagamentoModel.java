package com.algaworks.algafood.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel {

	@Schema(description = "ID da forma de pagamento", example = "1")
	private Long id;
	
	@Schema(description = "Descrição", example = "PIX")
	private String descricao;
}
