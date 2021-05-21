package com.algaworks.algafoodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.RestauranteModel;
import com.algaworks.algafoodapi.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public RestauranteModel toModel(Restaurante restaurante) {
		return mapper.map(restaurante, RestauranteModel.class);
	}
	
	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}
}
