package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.RestauranteResumoModel;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteResumoModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteResumoModel> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public RestauranteResumoModelAssembler() {
		super(RestauranteController.class, RestauranteResumoModel.class);
	}

	@Override
	public RestauranteResumoModel toModel(Restaurante restaurante) {
		var restauranteResumoModel = createModelWithId(restaurante.getId(), restaurante);
		
		mapper.map(restaurante, restauranteResumoModel);
		
		restauranteResumoModel.add(algaLinks.linkToRestaurantes());
		
		restauranteResumoModel.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
		
		return restauranteResumoModel;
	}
	
	@Override
	public CollectionModel<RestauranteResumoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
	}
}
