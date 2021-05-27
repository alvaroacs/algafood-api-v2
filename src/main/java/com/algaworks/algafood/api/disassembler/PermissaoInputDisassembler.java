package com.algaworks.algafood.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.PermissaoInput;
import com.algaworks.algafood.domain.model.Permissao;

@Component
public class PermissaoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Permissao toDomainObject(PermissaoInput permissaoInput) {
		return mapper.map(permissaoInput, Permissao.class);
	}
}
