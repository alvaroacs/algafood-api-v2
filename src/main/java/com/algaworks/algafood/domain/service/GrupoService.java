package com.algaworks.algafood.domain.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Grupo_;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;

	public List<Grupo> listar() {
		return grupoRepository.findAll();
	}

	public Grupo buscar(Long grupoId) {
		return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}

	@Transactional
	public Grupo adicionar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	@Transactional
	public Grupo atualizar(Long grupoId, Grupo grupoAtualizado) {
		var grupo = buscar(grupoId);
		BeanUtils.copyProperties(grupoAtualizado, grupo, Grupo_.ID);
		return grupoRepository.save(grupo);
	}

	@Transactional
	public void remover(Long grupoId) {
		var grupo = buscar(grupoId);
		try {
			grupoRepository.delete(grupo);
			grupoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removida, pois está em uso.", grupoId));
		}
	}
	
	@Transactional
	public Collection<Permissao> adicionarPermissao(Long grupoId) {
		var grupo = buscar(grupoId);
		return grupo.getPermissoes();
	}
}
