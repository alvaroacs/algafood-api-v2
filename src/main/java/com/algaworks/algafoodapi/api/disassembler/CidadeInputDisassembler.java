package com.algaworks.algafoodapi.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.input.CidadeInput;
import com.algaworks.algafoodapi.domain.model.Cidade;

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
