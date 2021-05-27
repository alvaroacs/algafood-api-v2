package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.algaworks.algafood.domain.event.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.exception.NegocioException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Entity
@Table(name = "pedido")
public class Pedido extends AbstractAggregateRoot<Pedido> {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String codigo = UUID.randomUUID().toString();

	private BigDecimal subtotal;

	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Embedded
	private Endereco enderecoEntrega;

	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;

	@CreationTimestamp
	@Column(name = "data_criacao")
	private OffsetDateTime dataCriacao;

	@Column(name = "data_confirmacao")
	private OffsetDateTime dataConfirmacao;

	@Column(name = "data_entrega")
	private OffsetDateTime dataEntrega;

	@Column(name = "data_cancelamento")
	private OffsetDateTime dataCancelamento;

	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id")
	private Usuario cliente;

	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "forma_pagamento_id")
	private FormaPagamento formaPagamento;

	@OneToMany(mappedBy = ItemPedido_.PEDIDO, cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public void calcularValorTotal() {
		getItens().forEach(ItemPedido::calcularPrecoTotal);

		setSubtotal(getItens().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add));

		setValorTotal(getSubtotal().add(getTaxaFrete()));
	}

	public void definirFrete() {
		setTaxaFrete(getRestaurante().getTaxaFrete());
	}

	public void atribuirPedidoAosItens() {
		getItens().forEach(item -> item.setPedido(this));
	}

	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now());
		
		registerEvent(new PedidoConfirmadoEvent(this));
	}
	
	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(OffsetDateTime.now());
	}
	
	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(OffsetDateTime.now());
		
		registerEvent(new PedidoCanceladoEvent(this));
	}

	private void setStatus(StatusPedido novoStatusPedido) {
		if (getStatus().naoPodeAlterarPara(novoStatusPedido)) {
			throw new NegocioException(String.format("Status do pedido '%s' não pode ser alterado de '%s' para '%s'",
					getCodigo(), getStatus().getDescricao(), novoStatusPedido.getDescricao()));
		}
		
		this.status = novoStatusPedido;
	}
}
