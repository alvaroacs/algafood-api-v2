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

import com.algaworks.algafoodapi.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafoodapi.api.disassembler.PermissaoInputDisassembler;
import com.algaworks.algafoodapi.api.model.PermissaoModel;
import com.algaworks.algafoodapi.api.model.input.PermissaoInput;
import com.algaworks.algafoodapi.api.util.ApiUtils;
import com.algaworks.algafoodapi.domain.service.PermissaoService;

@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController {

	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@Autowired
	private PermissaoInputDisassembler permissaoInputDisassembler;
	
	@Autowired
	private PermissaoService permissaoService;
	
	@GetMapping
	public ResponseEntity<List<PermissaoModel>> listar() {
		var permissoes = permissaoService.listar();
		var permissoesModel = permissaoModelAssembler.toCollectionModel(permissoes);
		return ResponseEntity.ok(permissoesModel);
	}
	
	@GetMapping("/{permissaoId}")
	public ResponseEntity<PermissaoModel> buscar(@PathVariable Long permissaoId) {
		var permissao = permissaoService.buscar(permissaoId);
		var permissaoModel = permissaoModelAssembler.toModel(permissao);
		return ResponseEntity.ok(permissaoModel);
	}
	
	@PostMapping
	public ResponseEntity<PermissaoModel> adicionar(@RequestBody @Valid PermissaoInput permissaoInput) {
		var permissao = permissaoInputDisassembler.toDomainObject(permissaoInput);
		permissao = permissaoService.adicionar(permissao);
		var permissaoModel = permissaoModelAssembler.toModel(permissao);
		return ResponseEntity.created(ApiUtils.uri(permissaoModel.getId())).body(permissaoModel);
	}
	
	@PutMapping("/{permissaoId}")
	public ResponseEntity<PermissaoModel> atualizar(@PathVariable Long permissaoId, @RequestBody PermissaoInput permissaoInput) {
		var permissao = permissaoInputDisassembler.toDomainObject(permissaoInput);
		permissao = permissaoService.atualizar(permissaoId, permissao);
		var permissaoModel = permissaoModelAssembler.toModel(permissao);
		return ResponseEntity.ok(permissaoModel);
	}
	
	@DeleteMapping("/{permissaoId}")
	public ResponseEntity<Void> remover(@PathVariable Long permissaoId) {
		permissaoService.remover(permissaoId);
		return ResponseEntity.noContent().build();
	}
}
