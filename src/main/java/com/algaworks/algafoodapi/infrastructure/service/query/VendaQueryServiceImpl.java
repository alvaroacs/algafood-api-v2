package com.algaworks.algafoodapi.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.algaworks.algafoodapi.domain.dto.VendaDiaria;
import com.algaworks.algafoodapi.domain.filter.VendaDiariaFilter;
import com.algaworks.algafoodapi.domain.model.Pedido;
import com.algaworks.algafoodapi.domain.model.Pedido_;
import com.algaworks.algafoodapi.domain.model.StatusPedido;
import com.algaworks.algafoodapi.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		var builder = entityManager.getCriteriaBuilder();
		var criteria = builder.createQuery(VendaDiaria.class);
		var root = criteria.from(Pedido.class);

		var functionConvertTzDataCriacao = builder.function("CONVERT_TZ", Date.class, root.get(Pedido_.DATA_CRIACAO),
				builder.literal("+00:00"), builder.literal(timeOffset));
		var functionDataCriacao = builder.function("DATE", Date.class, functionConvertTzDataCriacao);

		criteria.select(builder.construct(VendaDiaria.class, functionDataCriacao, builder.count(root.get(Pedido_.ID)),
				builder.sum(root.get(Pedido_.VALOR_TOTAL))));

		var predicates = new ArrayList<Predicate>();
		predicates.add(root.get(Pedido_.STATUS).in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

		if (filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Pedido_.DATA_CRIACAO), filtro.getDataCriacaoInicio()));
		}

		if (filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Pedido_.DATA_CRIACAO), filtro.getDataCriacaoFim()));
		}

		if (filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get(Pedido_.RESTAURANTE), filtro.getRestauranteId()));
		}

		criteria.where(predicates.toArray(new Predicate[predicates.size()]));

		criteria.groupBy(functionDataCriacao);

		return entityManager.createQuery(criteria).getResultList();
	}

}
