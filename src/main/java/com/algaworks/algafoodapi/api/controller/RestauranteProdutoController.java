package com.algaworks.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.ProdutoModelAssembler;
import com.algaworks.algafoodapi.api.disassembler.ProdutoInputDisassembler;
import com.algaworks.algafoodapi.api.model.ProdutoModel;
import com.algaworks.algafoodapi.api.model.input.ProdutoInput;
import com.algaworks.algafoodapi.api.util.ApiUtils;
import com.algaworks.algafoodapi.domain.service.ProdutoService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController {

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> listar(@PathVariable Long restauranteId, 
			@RequestParam(name = "incluir-inativos", required = false, defaultValue = "false") boolean incluirInativos) {
		var produtos = produtoService.listar(restauranteId, incluirInativos);
		var produtosModel = produtoModelAssembler.toCollectionModel(produtos);
		return ResponseEntity.ok(produtosModel);
	}
	
	@GetMapping("/{produtoId}")
	public ResponseEntity<ProdutoModel> buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		var produto = produtoService.buscar(restauranteId, produtoId);
		var produtoModel = produtoModelAssembler.toModel(produto);
		return ResponseEntity.ok(produtoModel);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoModel> adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
		var produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto = produtoService.adicionar(restauranteId, produto);
		var produtoModel = produtoModelAssembler.toModel(produto);
		return ResponseEntity.created(ApiUtils.uri(produtoModel.getId())).body(produtoModel);
	}
	
	@PutMapping("/{produtoId}")
	public ResponseEntity<ProdutoModel> atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, 
			@RequestBody @Valid ProdutoInput produtoInput) {
		var produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto = produtoService.atualizar(restauranteId, produtoId, produto);
		var produtoModel = produtoModelAssembler.toModel(produto);
		return ResponseEntity.ok(produtoModel);
	}
}
