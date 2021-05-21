package com.algaworks.algafoodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.FotoProdutoModel;
import com.algaworks.algafoodapi.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public FotoProdutoModel toModel(FotoProduto fotoProduto) {
		return mapper.map(fotoProduto, FotoProdutoModel.class);
	}
}
