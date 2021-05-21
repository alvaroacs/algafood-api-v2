package com.algaworks.algafoodapi.infrastructure.repository;

import static com.algaworks.algafoodapi.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafoodapi.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.model.Restaurante_;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Lazy
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		var criteriaBuilder = entityManager.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);
		var root = criteriaQuery.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if (StringUtils.hasText(nome)) {
			predicates.add(criteriaBuilder.like(root.get(Restaurante_.NOME), "%" + nome + "%"));
		}
		
		if (taxaFreteInicial != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Restaurante_.TAXA_FRETE), taxaFreteInicial));
		}
		
		if (taxaFreteFinal != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Restaurante_.TAXA_FRETE), taxaFreteFinal));
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		
		var typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}

	@Override
	public List<FormaPagamento> findAllFormasPagamento(Long restauranteId) {
		var builder = entityManager.getCriteriaBuilder();
		var criteriaQuery = builder.createQuery(FormaPagamento.class);
		var root = criteriaQuery.from(Restaurante.class);
		
		criteriaQuery.where(builder.equal(root.get(Restaurante_.ID), restauranteId));
		
		criteriaQuery.select(root.get(Restaurante_.FORMAS_PAGAMENTO));
		
		var typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
}
