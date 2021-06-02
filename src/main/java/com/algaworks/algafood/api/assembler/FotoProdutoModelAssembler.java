package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteProdutoFotoController;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public FotoProdutoModelAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
	}
	
	@Override
	public FotoProdutoModel toModel(FotoProduto fotoProduto) {
		var fotoProdutoModel = mapper.map(fotoProduto, FotoProdutoModel.class);
		
		fotoProdutoModel.add(algaLinks.linkToProdutoFoto(fotoProduto.getProduto().getRestaurante().getId(), fotoProduto.getId()));
		
		fotoProdutoModel.add(algaLinks.linkToProduto(fotoProduto.getProduto().getRestaurante().getId(), fotoProduto.getId(), "produto"));
		
		return fotoProdutoModel;
	}
}
