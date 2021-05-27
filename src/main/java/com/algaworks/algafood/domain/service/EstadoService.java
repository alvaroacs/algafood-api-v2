package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Estado_;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois está em uso.";

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	public Estado buscar(Long estadoId) {
		return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradaException(estadoId));
	}

	@Transactional
	public Estado adicionar(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Transactional
	public Estado atualizar(Long estadoId, Estado estadoAtualizado) {
		var estado = buscar(estadoId);

		BeanUtils.copyProperties(estadoAtualizado, estado, Estado_.ID);

		return estadoRepository.save(estado);
	}

	@Transactional
	public void remover(Long estadoId) {
		var estado = buscar(estadoId);

		try {
			estadoRepository.delete(estado);
			estadoRepository.flush();
//		} catch (EmptyResultDataAccessException e) {
//			throw new EstadoNaoEncontradaException(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}
}
