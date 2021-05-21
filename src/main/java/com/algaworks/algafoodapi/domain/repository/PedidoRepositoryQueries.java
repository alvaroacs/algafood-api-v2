package com.algaworks.algafoodapi.domain.repository;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafoodapi.domain.filter.PedidoFilter;
import com.algaworks.algafoodapi.domain.model.Pedido;

public interface PedidoRepositoryQueries {

	List<Pedido> findAll(PedidoFilter pedidoFilter);
	
	Optional<Pedido> findByCodigo(String codigo);
}
