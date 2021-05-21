package com.algaworks.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafoodapi.api.disassembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafoodapi.api.model.FormaPagamentoModel;
import com.algaworks.algafoodapi.api.model.input.FormaPagamentoInput;
import com.algaworks.algafoodapi.api.util.ApiUtils;
import com.algaworks.algafoodapi.domain.service.FormaPagamentoService;

@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@GetMapping
	public ResponseEntity<List<FormaPagamentoModel>> listar() {
		var formasPagamento = formaPagamentoService.listar();

		var formasPagamentoModel = formaPagamentoModelAssembler.toCollectionModel(formasPagamento);
		return ResponseEntity.ok(formasPagamentoModel);
	}

	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId) {
		var formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
		var formaPagamentoModel = formaPagamentoModelAssembler.toModel(formaPagamento);

		return ResponseEntity.ok(formaPagamentoModel);
	}

	@PostMapping
	public ResponseEntity<FormaPagamentoModel> adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		var formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
		formaPagamento = formaPagamentoService.adicionar(formaPagamento);
		var formaPagamentoModel = formaPagamentoModelAssembler.toModel(formaPagamento);
		return ResponseEntity.created(ApiUtils.uri(formaPagamentoModel.getId())).body(formaPagamentoModel);
	}

	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoModel> atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		var formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
		formaPagamento = formaPagamentoService.atualizar(formaPagamentoId, formaPagamento);
		var formaPagamentoModel = formaPagamentoModelAssembler.toModel(formaPagamento);
		return ResponseEntity.ok(formaPagamentoModel);
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoModel> remover(@PathVariable Long formaPagamentoId) {
		formaPagamentoService.remover(formaPagamentoId);		
		return ResponseEntity.noContent().build();
	}
}
