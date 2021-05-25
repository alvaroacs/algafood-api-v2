package com.algaworks.algafoodapi.domain.event;

import com.algaworks.algafoodapi.domain.model.Pedido;

import lombok.Getter;

import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {

	private Pedido pedido;
}
