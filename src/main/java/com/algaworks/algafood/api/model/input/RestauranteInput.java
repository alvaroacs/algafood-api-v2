package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInput {
	
	@Schema(description = "Nome", example = "Thai Gourmet", required = true)
	@NotBlank
	private String nome;
	
	@Schema(description = "Taxa Frete", example = "1", required = true)
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@Schema(description = "Cozinha", implementation = CozinhaIdInput.class, required = true)
	@Valid
	@NotNull
	private CozinhaIdInput cozinha;
	
	@Schema(description = "Endereço", implementation = EnderecoInput.class, required = true)
	@Valid
	@NotNull
	private EnderecoInput endereco;
}
