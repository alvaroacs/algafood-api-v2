package com.algaworks.algafoodapi.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.input.PedidoInput;
import com.algaworks.algafoodapi.domain.model.Pedido;

@Component
public class PedidoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Pedido toDomainObject(PedidoInput pedidoInput) {
		return mapper.map(pedidoInput, Pedido.class);
	}
}
