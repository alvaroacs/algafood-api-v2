package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;

public interface PedidoRepositoryQueries {

	List<Pedido> findAll(PedidoFilter pedidoFilter);
	
	Optional<Pedido> findByCodigo(String codigo);
}
