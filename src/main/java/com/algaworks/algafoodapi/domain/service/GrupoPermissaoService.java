package com.algaworks.algafoodapi.domain.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.model.Permissao;

@Service
public class GrupoPermissaoService {

	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private PermissaoService permissaoService;
	
	public Collection<Permissao> listar(Long grupoId) {
		var grupo = grupoService.buscar(grupoId);
		return grupo.getPermissoes();
	}
	
	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		var grupo = grupoService.buscar(grupoId);
		var permissao = permissaoService.buscar(permissaoId);
		
		grupo.associarPermissao(permissao);
	}
	
	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		var grupo = grupoService.buscar(grupoId);
		var permissao = permissaoService.buscar(permissaoId);
		
		grupo.desassociarPermissao(permissao);
	}
}
