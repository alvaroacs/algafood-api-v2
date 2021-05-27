package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

	@Schema(description = "Nome", example = "Camarão tailandês", required = true)
	@NotBlank
	private String nome;

	@Schema(description = "Descrição", example = "16 camarões grandes ao molho picante", required = true)
	@NotBlank
	private String descricao;

	@Schema(description = "Preço do produto", example = "1", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	
	@Schema(description = "Se está ativo", example = "true", required = true)
	@NotNull
	private Boolean ativo;
}
