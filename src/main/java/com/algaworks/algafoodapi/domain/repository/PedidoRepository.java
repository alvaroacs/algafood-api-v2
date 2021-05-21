package com.algaworks.algafoodapi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.algaworks.algafoodapi.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
	
	Optional<Pedido> findByCodigo(String codigo);
}
