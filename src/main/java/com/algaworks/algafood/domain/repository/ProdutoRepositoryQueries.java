package com.algaworks.algafood.domain.repository;

import java.util.Optional;

import com.algaworks.algafood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

	FotoProduto save(FotoProduto foto);
	
	void delete(FotoProduto foto);
	
	Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);
}
