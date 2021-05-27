package com.algaworks.algafood.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Cidade toDomainObject(CidadeInput cidadeInput) {
		return mapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		mapper.map(cidadeInput, cidade);
	}
}
