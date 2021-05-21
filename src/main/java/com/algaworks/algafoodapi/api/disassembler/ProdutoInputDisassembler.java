package com.algaworks.algafoodapi.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.input.ProdutoInput;
import com.algaworks.algafoodapi.domain.model.Produto;

@Component
public class ProdutoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Produto toDomainObject(ProdutoInput produtoInput) {
		return mapper.map(produtoInput, Produto.class);
	}
}
