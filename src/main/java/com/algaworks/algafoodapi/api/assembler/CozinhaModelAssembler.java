package com.algaworks.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.CozinhaModel;
import com.algaworks.algafoodapi.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public CozinhaModel toModel(Cozinha cozinha) {
		return mapper.map(cozinha, CozinhaModel.class);
	}
	
	public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
		return cozinhas.stream().map(cozinha -> toModel(cozinha)).collect(Collectors.toList());
	}
}
