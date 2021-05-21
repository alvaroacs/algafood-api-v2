package com.algaworks.algafoodapi.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.input.GrupoInput;
import com.algaworks.algafoodapi.domain.model.Grupo;

@Component
public class GrupoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Grupo toDomainObject(GrupoInput grupoInput) {
		return mapper.map(grupoInput, Grupo.class);
	}
}
