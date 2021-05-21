package com.algaworks.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafoodapi.api.model.FormaPagamentoModel;
import com.algaworks.algafoodapi.domain.service.RestauranteFormaPagamentoService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController {

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@Autowired
	private RestauranteFormaPagamentoService restauranteFormaPagamentoService;
	
	@GetMapping
	public ResponseEntity<List<FormaPagamentoModel>> listar(@PathVariable Long restauranteId) {
		var formasPagamento = restauranteFormaPagamentoService.listar(restauranteId);
		var formasPagamentoModel = formaPagamentoModelAssembler.toCollectionModel(formasPagamento);
		return ResponseEntity.ok(formasPagamentoModel);
	}
	
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<Void> associarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteFormaPagamentoService.associarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<Void> desassociarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteFormaPagamentoService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}
}
