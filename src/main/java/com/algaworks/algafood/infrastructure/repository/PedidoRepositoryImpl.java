package com.algaworks.algafood.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Cidade_;
import com.algaworks.algafood.domain.model.Endereco_;
import com.algaworks.algafood.domain.model.ItemPedido_;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Pedido_;
import com.algaworks.algafood.domain.model.Restaurante_;
import com.algaworks.algafood.domain.repository.PedidoRepositoryQueries;

@Repository
public class PedidoRepositoryImpl implements PedidoRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Pedido> findAll(PedidoFilter pedidoFilter) {
		var builder = entityManager.getCriteriaBuilder();
		var criteria = builder.createQuery(Pedido.class);
		var root = criteria.from(Pedido.class);

		root.fetch(Pedido_.RESTAURANTE).fetch(Restaurante_.COZINHA);
		root.fetch(Pedido_.CLIENTE);
		root.fetch(Pedido_.FORMA_PAGAMENTO);
		
		criteria.where(usandoFiltro(pedidoFilter, builder, root));

		var typedQuery = entityManager.createQuery(criteria);

		return typedQuery.getResultList();
	}

	@Override
	public Optional<Pedido> findByCodigo(String codigo) {
		try {
			var builder = entityManager.getCriteriaBuilder();
			var criteria = builder.createQuery(Pedido.class);
			var root = criteria.from(Pedido.class);

			root.fetch(Pedido_.RESTAURANTE).fetch(Restaurante_.COZINHA);
			root.fetch(Pedido_.CLIENTE);
			root.fetch(Pedido_.FORMA_PAGAMENTO);
			root.fetch(Pedido_.ITENS).fetch(ItemPedido_.PRODUTO);
			root.fetch(Pedido_.ENDERECO_ENTREGA).fetch(Endereco_.CIDADE).fetch(Cidade_.ESTADO);
			
			criteria.where(builder.equal(root.get(Pedido_.CODIGO), codigo));

			var typedQuery = entityManager.createQuery(criteria);

			return Optional.ofNullable(typedQuery.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}

	}

	private Predicate[] usandoFiltro(PedidoFilter filtro, CriteriaBuilder builder, Root<Pedido> root) {
		var predicates = new ArrayList<Predicate>();
		
		if (filtro.getClienteId() != null) {
			predicates.add(builder.equal(root.get(Pedido_.CLIENTE), filtro.getClienteId()));
		}
		
		if (filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get(Pedido_.RESTAURANTE), filtro.getRestauranteId()));
		}
		
		if (filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Pedido_.DATA_CRIACAO), filtro.getDataCriacaoInicio()));
		}
		
		if (filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Pedido_.DATA_CRIACAO), filtro.getDataCriacaoFim()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
