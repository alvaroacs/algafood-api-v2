package com.algaworks.algafood.api.model.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {

	@Schema(description = "Endereço de entrega", implementation = EnderecoInput.class, required = true)
	@Valid
	@NotNull
	private EnderecoInput enderecoEntrega;

	@Schema(description = "Restaurante", implementation = RestauranteIdInput.class, required = true)
	@Valid
	@NotNull
	private RestauranteIdInput restaurante;

	@Schema(description = "Forma de pagamento", implementation = FormaPagamentoIdInput.class, required = true)
	@Valid
	@NotNull
	private FormaPagamentoIdInput formaPagamento;
	
	@ArraySchema(schema = @Schema(description = "Itens", implementation = ItemPedidoInput.class, required = true))
	@Valid
	@NotNull
	@Size(min = 1)
	private List<ItemPedidoInput> itens = new ArrayList<>();
}
