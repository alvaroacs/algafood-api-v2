package com.algaworks.algafoodapi.domain.service;

import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.PermissaoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Permissao;
import com.algaworks.algafoodapi.domain.model.Permissao_;
import com.algaworks.algafoodapi.domain.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;

	public Collection<Permissao> listar() {
		return permissaoRepository.findAll();
	}

	public Permissao buscar(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}

	@Transactional
	public Permissao adicionar(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}

	@Transactional
	public Permissao atualizar(Long permissaoId, Permissao permissaoAtualizada) {
		var permissao = buscar(permissaoId);

		BeanUtils.copyProperties(permissaoAtualizada, permissao, Permissao_.ID);

		return permissaoRepository.save(permissao);
	}

	@Transactional
	public void remover(Long permissaoId) {
		var permissao = buscar(permissaoId);
		try {
			permissaoRepository.delete(permissao);
			permissaoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Permissao de código %d não pode ser removida, pois está em uso.", permissaoId));
		}
	}
}
