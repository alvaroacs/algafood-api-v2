package com.algaworks.algafoodapi.domain.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.model.Grupo;

@Service
public class UsuarioGrupoService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoService grupoService;
	
	public Collection<Grupo> listar(Long usuarioId) {
		var usuario = usuarioService.buscar(usuarioId);
		return usuario.getGrupos();
	}
	
	@Transactional
	public void associar(Long usuarioId, Long grupoId) {
		var usuario = usuarioService.buscar(usuarioId);
		var grupo = grupoService.buscar(grupoId);
		
		usuario.associar(grupo);
	}
	
	@Transactional
	public void desassociar(Long usuarioId, Long grupoId) {
		var usuario = usuarioService.buscar(usuarioId);
		var grupo = grupoService.buscar(grupoId);
		
		usuario.desassociar(grupo);
	}
}
