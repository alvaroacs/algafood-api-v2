package com.algaworks.algafood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.FotoProduto_;
import com.algaworks.algafood.domain.model.Produto_;
import com.algaworks.algafood.domain.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public FotoProduto save(FotoProduto foto) {
		return entityManager.merge(foto);
	}

	@Override
	public Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId) {
		try {
			var builder = entityManager.getCriteriaBuilder();
			var criteria = builder.createQuery(FotoProduto.class);
			var root = criteria.from(FotoProduto.class);
			var rootProduto = root.join(FotoProduto_.PRODUTO);
			
			criteria.where(builder.equal(root.get(FotoProduto_.PRODUTO), produtoId),
					builder.equal(rootProduto.get(Produto_.RESTAURANTE), restauranteId));

			return Optional.ofNullable(entityManager.createQuery(criteria).getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public void delete(FotoProduto foto) {
		entityManager.remove(foto);
	}

}
