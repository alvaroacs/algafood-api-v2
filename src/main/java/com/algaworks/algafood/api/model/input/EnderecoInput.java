package com.algaworks.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@Schema(description = "CEP", example = "00000-000", required = true)
	@NotBlank
	private String cep;

	@Schema(description = "Logradouro", example = "Rua das árvores", required = true)
	@NotBlank
	private String logradouro;

	@Schema(description = "Número", example = "1A", required = true)
	@NotBlank
	private String numero;

	@Schema(description = "Complemento", example = "Ao lado da igreja", required = false)
	private String complemento;

	@Schema(description = "Bairro", example = "Barra Funda", required = true)
	@NotBlank
	private String bairro;

	@Schema(description = "Cidade", implementation = CidadeIdInput.class, required = true)
	@Valid
	@NotNull
	private CidadeIdInput cidade;
}
