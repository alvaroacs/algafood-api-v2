package com.algaworks.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.ProdutoModel;
import com.algaworks.algafoodapi.domain.model.Produto;

@Component
public class ProdutoModelAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public ProdutoModel toModel(Produto produto) {
		return mapper.map(produto, ProdutoModel.class);
	}
	
	public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
		return produtos.stream().map(produto -> toModel(produto)).collect(Collectors.toList());
	}
}
