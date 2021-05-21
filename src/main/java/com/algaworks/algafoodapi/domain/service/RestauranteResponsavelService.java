package com.algaworks.algafoodapi.domain.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.model.Usuario;

@Service
public class RestauranteResponsavelService {

	@Autowired
	private RestauranteService restauranteSerive;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Collection<Usuario> listar(Long restauranteId) {
		var restaurante = restauranteSerive.buscar(restauranteId);
		return restaurante.getResponsaveis();
	}
	
	@Transactional
	public void associar(Long restauranteId, Long responsavelId) {
		var restaurante = restauranteSerive.buscar(restauranteId);
		var responsavel = usuarioService.buscar(responsavelId);
		
		restaurante.associar(responsavel);
	}
	
	@Transactional
	public void desassociar(Long restauranteId, Long responsavelId) {
		var restaurante = restauranteSerive.buscar(restauranteId);
		var responsavel = usuarioService.buscar(responsavelId);
		
		restaurante.desassociar(responsavel);
	}
}
