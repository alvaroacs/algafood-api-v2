package com.algaworks.algafoodapi.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.model.UsuarioModel;
import com.algaworks.algafoodapi.domain.model.Usuario;

@Component
public class UsuarioModelAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public UsuarioModel toModel(Usuario usuario) {
		return mapper.map(usuario, UsuarioModel.class);
	}
	
	public List<UsuarioModel> toCollectionModel(Collection<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());
	}
}
