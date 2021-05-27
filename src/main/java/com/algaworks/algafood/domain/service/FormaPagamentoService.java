package com.algaworks.algafood.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.FormaPagamento_;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
	
	private static final String MSG_ESTADO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso.";

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	public List<FormaPagamento> listar() {
		return formaPagamentoRepository.findAll();
	}
	
	public FormaPagamento buscar(Long formaPagamentoId) {
		return formaPagamentoRepository.findById(formaPagamentoId)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
	}
	
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	public FormaPagamento atualizar(Long formaPagamentoId, FormaPagamento formaPagamentoAtualizado) {
		var formaPagamento = buscar(formaPagamentoId);
		
		BeanUtils.copyProperties(formaPagamentoAtualizado, formaPagamento, FormaPagamento_.ID);
		
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	public void remover(Long formaPagamentoId) { 
		var formaPagamento = buscar(formaPagamentoId);
		
		try {
			formaPagamentoRepository.delete(formaPagamento);
			formaPagamentoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, formaPagamentoId));
		}
	}
	
	public OffsetDateTime getDataUltimaAtualizacao() {
		return formaPagamentoRepository.getDataUltimaAtualizacao();
	}
}
