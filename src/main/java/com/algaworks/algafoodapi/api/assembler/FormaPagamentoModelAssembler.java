package com.algaworks.algafoodapi.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.FormaPagamentoModel;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		return mapper.map(formaPagamento, FormaPagamentoModel.class);
	}
	
	public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> formasPagamento) {
		return formasPagamento.stream().map(formaPagamento -> toModel(formaPagamento)).collect(Collectors.toList());
	}
}
