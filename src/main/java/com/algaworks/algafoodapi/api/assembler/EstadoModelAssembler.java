package com.algaworks.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.EstadoModel;
import com.algaworks.algafoodapi.domain.model.Estado;

@Component
public class EstadoModelAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public EstadoModel toModel(Estado estado) {
		return mapper.map(estado, EstadoModel.class);
	}
	
	public List<EstadoModel> toCollectionModel(List<Estado> estados) {
		return estados.stream().map(estado -> toModel(estado)).collect(Collectors.toList());
	}
}
