package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.algafood.domain.model.Restaurante;

public interface RestauranteRepository
		extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();
	
	boolean existsResponsavel(Long restauranteId, Long usuarioId);
}
