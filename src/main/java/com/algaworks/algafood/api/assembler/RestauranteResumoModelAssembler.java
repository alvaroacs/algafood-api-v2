package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.RestauranteResumoModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteResumoModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteResumoModel> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public RestauranteResumoModelAssembler() {
		super(RestauranteController.class, RestauranteResumoModel.class);
	}

	@Override
	public RestauranteResumoModel toModel(Restaurante restaurante) {
		var restauranteResumoModel = createModelWithId(restaurante.getId(), restaurante);
		
		mapper.map(restaurante, restauranteResumoModel);
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			restauranteResumoModel.add(algaLinks.linkToRestaurantes());
		}
		
		if (algaSecurity.podeConsultarCozinhas()) {
			restauranteResumoModel.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
		}
		
		return restauranteResumoModel;
	}
	
	@Override
	public CollectionModel<RestauranteResumoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		var collectionModel = super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			collectionModel.add(algaLinks.linkToRestaurantes());
		}
		
		return collectionModel;
	}
}
