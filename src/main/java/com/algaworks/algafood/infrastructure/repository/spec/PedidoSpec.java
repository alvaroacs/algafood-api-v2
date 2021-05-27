package com.algaworks.algafood.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Pedido_;
import com.algaworks.algafood.domain.model.Restaurante_;

public class PedidoSpec {

	public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
		return (root, query, builder) -> {
			if (Pedido.class.equals(query.getResultType())) {
				root.fetch(Pedido_.RESTAURANTE).fetch(Restaurante_.COZINHA);
				root.fetch(Pedido_.CLIENTE);
				root.fetch(Pedido_.FORMA_PAGAMENTO);
			}

			var predicates = new ArrayList<Predicate>();

			if (filtro.getClienteId() != null) {
				predicates.add(builder.equal(root.get(Pedido_.CLIENTE), filtro.getClienteId()));
			}

			if (filtro.getRestauranteId() != null) {
				predicates.add(builder.equal(root.get(Pedido_.RESTAURANTE), filtro.getRestauranteId()));
			}

			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
