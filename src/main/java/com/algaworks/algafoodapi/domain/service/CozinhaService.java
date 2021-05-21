package com.algaworks.algafoodapi.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.model.Cozinha_;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso.";

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Page<Cozinha> listar(Pageable pageable) {
		return cozinhaRepository.findAll(pageable);
	}

	public Cozinha buscar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}

	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	@Transactional
	public Cozinha atualizar(Long cozinhaId, Cozinha cozinhaAtualizada) {
		var cozinha = buscar(cozinhaId);

		BeanUtils.copyProperties(cozinhaAtualizada, cozinha, Cozinha_.ID);

		return cozinhaRepository.save(cozinha);
	}

	@Transactional
	public void remover(Long cozinhaId) {
		var cozinha = buscar(cozinhaId);
		try {
			cozinhaRepository.delete(cozinha);
			cozinhaRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, cozinhaId));
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
