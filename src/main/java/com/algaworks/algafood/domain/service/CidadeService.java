package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cidade_;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoService estadoService;

	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Cidade buscar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}

	@Transactional
	public Cidade adicionar(Cidade cidade) {
		try {
			var estado = estadoService.buscar(cidade.getEstado().getId());

			cidade.setEstado(estado);

			return cidadeRepository.save(cidade);
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Transactional
	public Cidade atualizar(Long cidadeId, Cidade cidadeAtualizada) {
		var cidade = buscar(cidadeId);

		try {
			if (cidadeAtualizada.getEstado() != null) {
				var estado = estadoService.buscar(cidadeAtualizada.getEstado().getId());
				BeanUtils.copyProperties(cidadeAtualizada, cidade, Cidade_.ID);
				cidade.setEstado(estado);
			} else {
				BeanUtils.copyProperties(cidadeAtualizada, cidade, Cidade_.ID, Cidade_.ESTADO);
			}

			return cidadeRepository.save(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Transactional
	public void remover(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
			cidadeRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removida, pois está em uso.", cidadeId));
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(cidadeId);
		}
	}
}
