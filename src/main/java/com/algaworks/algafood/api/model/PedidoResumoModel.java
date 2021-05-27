package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoModel {

	@Schema(description = "Código do pedido", example = "b5741512-8fbc-47fa-9ac1-b530354fc0ff")
	private String codigo;

	@Schema(description = "Subtotal do pedido", example = "3")
	private BigDecimal subtotal;

	@Schema(description = "Taxa frete", example = "1")
	private BigDecimal taxaFrete;

	@Schema(description = "Valor total do pedido", example = "4")
	private BigDecimal valorTotal;

	@Schema(description = "Status do pedido", example = "ENTREGUE")
	private String status;

	@Schema(description = "Data de criação do pedido", example = "2019-10-30T21:10:00Z")
	private OffsetDateTime dataCriacao;

	@Schema(description = "Cliente que realizou o pedido", implementation = UsuarioModel.class)
	private UsuarioModel cliente;
	
	@Schema(description = "Restaurante onde foi feito o pedido", implementation = RestauranteResumoModel.class)
	private RestauranteResumoModel restaurante;
}
