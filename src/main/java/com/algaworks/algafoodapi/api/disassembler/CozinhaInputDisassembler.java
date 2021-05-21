package com.algaworks.algafoodapi.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.input.CozinhaInput;
import com.algaworks.algafoodapi.domain.model.Cozinha;

@Component
public class CozinhaInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Cozinha toDomainObject(CozinhaInput cozinhaInput) {
		return mapper.map(cozinhaInput, Cozinha.class);
	}
}
