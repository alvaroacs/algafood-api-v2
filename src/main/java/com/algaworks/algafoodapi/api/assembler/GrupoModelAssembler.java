package com.algaworks.algafoodapi.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.GrupoModel;
import com.algaworks.algafoodapi.domain.model.Grupo;

@Component
public class GrupoModelAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public GrupoModel toModel(Grupo grupo) {
		return mapper.map(grupo, GrupoModel.class);
	}
	
	public List<GrupoModel> toCollectionModel(Collection<Grupo> grupos) {
		return grupos.stream().map(grupo -> toModel(grupo)).collect(Collectors.toList());
	}
}
