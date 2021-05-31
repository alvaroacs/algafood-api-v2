package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel extends RepresentationModel<EnderecoModel> {

	@Schema(description = "CEP", example = "00000-000")
	private String cep;

	@Schema(description = "Nome da rua", example = "Rua das Névoas")
	private String logradouro;

	@Schema(description = "Número", example = "1A")
	private String numero;

	@Schema(description = "Complemento do endereço", example = "São Paulo")
	private String complemento;

	@Schema(description = "Nome do bairro", example = "Barra Funda")
	private String bairro;

	@Schema(description = "Nome da cidade", example = "São Paulo")
	private String cidade;

	@Schema(description = "Nome do estado", example = "São Paulo")
	private String estado;
}
