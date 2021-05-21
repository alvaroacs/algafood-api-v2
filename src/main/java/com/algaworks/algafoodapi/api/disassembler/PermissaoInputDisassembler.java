package com.algaworks.algafoodapi.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.input.PermissaoInput;
import com.algaworks.algafoodapi.domain.model.Permissao;

@Component
public class PermissaoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Permissao toDomainObject(PermissaoInput permissaoInput) {
		return mapper.map(permissaoInput, Permissao.class);
	}
}
