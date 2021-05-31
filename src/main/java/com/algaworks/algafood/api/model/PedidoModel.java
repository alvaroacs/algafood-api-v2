package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoModel extends RepresentationModel<PedidoModel> {

	@Schema(description = "Código do pedido", example = "b5741512-8fbc-47fa-9ac1-b530354fc0ff")
	private String codigo;

	@Schema(description = "Subtotal do pedido", example = "3")
	private BigDecimal subtotal;

	@Schema(description = "Taxa frete", example = "1")
	private BigDecimal taxaFrete;

	@Schema(description = "Valor total do pedido", example = "4")
	private BigDecimal valorTotal;

	@Schema(implementation = EnderecoModel.class, description = "Endereço de entrega do pedido")
	private EnderecoModel enderecoEntrega;

	@Schema(description = "Status do pedido", example = "ENTREGUE")
	private String status;

	@Schema(description = "Data de criação do pedido", example = "2019-10-30T21:10:00Z")
	private OffsetDateTime dataCriacao;

	@Schema(description = "Data de confirmação do restaurante", example = "2019-10-30T21:12:00Z")
	private OffsetDateTime dataConfirmacao;

	@Schema(description = "Data de entrega do pedido", example = "2019-10-30T22:15:00Z")
	private OffsetDateTime dataEntrega;

	@Schema(description = "Data de cancelamento do pedido", example = "2019-10-30T21:10:10Z")
	private OffsetDateTime dataCancelamento;

	@Schema(description = "Cliente que realizou o pedido", implementation = UsuarioModel.class)
	private UsuarioModel cliente;

	@Schema(description = "Restaurante onde foi feito o pedido", implementation = RestauranteResumoModel.class)
	private RestauranteResumoModel restaurante;

	@Schema(description = "Forma do pagamento", implementation = FormaPagamentoModel.class)
	private FormaPagamentoModel formaPagamento;
	
	@ArraySchema(schema = @Schema(description = "Itens do pedido", implementation =  ItemPedidoModel.class))
	private List<ItemPedidoModel> itens;
}
