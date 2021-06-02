package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}
	
	@Override
	public RestauranteModel toModel(Restaurante restaurante) {
		var restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		
		mapper.map(restaurante, restauranteModel);
		
		restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
		restauranteModel.add(algaLinks.linkToRestauranteFormaPagamento(restaurante.getId(), "formasPagamento"));
		restauranteModel.add(algaLinks.linkToRestauranteResponsaveis(restaurante.getId(), "responsaveis"));
		
		if (restaurante.podeAtivar()) {
			restauranteModel.add(algaLinks.linkToRestauranteAtivar(restaurante.getId(), "ativar"));			
		}
		
		if (restaurante.podeInativar()) {
			restauranteModel.add(algaLinks.linkToRestauranteInativar(restaurante.getId(), "inativar"));			
		}
		
		if (restaurante.podeAbrir()) {
			restauranteModel.add(algaLinks.linkToRestauranteAbrir(restaurante.getId(), "abrir"));			
		}
		
		if (restaurante.podeFechar()) {
			restauranteModel.add(algaLinks.linkToRestauranteFechar(restaurante.getId(), "fechar"));			
		}
		
		restauranteModel.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));
		
		if (restaurante.getEndereco() != null && restaurante.getEndereco().getCidade() != null) {
			restauranteModel.getEndereco().add(algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
		}
		
		restauranteModel.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
		
		return restauranteModel;
	}
	
	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
	}
}
