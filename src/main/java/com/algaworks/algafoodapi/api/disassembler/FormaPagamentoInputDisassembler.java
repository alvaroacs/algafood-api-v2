package com.algaworks.algafoodapi.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.input.FormaPagamentoInput;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;

@Component
public class FormaPagamentoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
		return mapper.map(formaPagamentoInput, FormaPagamento.class);
	}
}
