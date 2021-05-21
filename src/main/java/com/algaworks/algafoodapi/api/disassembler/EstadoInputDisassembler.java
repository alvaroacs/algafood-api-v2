package com.algaworks.algafoodapi.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.input.EstadoInput;
import com.algaworks.algafoodapi.domain.model.Estado;

@Component
public class EstadoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Estado toDomainObject(EstadoInput estadoInput) {
		return mapper.map(estadoInput, Estado.class);
	}
}
