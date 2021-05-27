package com.algaworks.algafood.domain.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.FormaPagamento;

@Service
public class RestauranteFormaPagamentoService {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Transactional(readOnly = true)
	public Collection<FormaPagamento> listar(Long restauranteId) {
		var restaurante = restauranteService.buscar(restauranteId);
		return restaurante.getFormasPagamento();
//		return restauranteService.listarFormasPagamento(restauranteId);
	}
	
	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		var restaurante = restauranteService.buscar(restauranteId);
		var formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
		
		restaurante.associar(formaPagamento);
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		var restaurante = restauranteService.buscar(restauranteId);
		var formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
		
		restaurante.desassociar(formaPagamento);
	}
}
