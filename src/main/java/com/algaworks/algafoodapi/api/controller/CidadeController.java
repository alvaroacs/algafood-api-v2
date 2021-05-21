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

import com.algaworks.algafoodapi.api.assembler.CidadeModelAssembler;
import com.algaworks.algafoodapi.api.disassembler.CidadeInputDisassembler;
import com.algaworks.algafoodapi.api.model.CidadeModel;
import com.algaworks.algafoodapi.api.model.input.CidadeInput;
import com.algaworks.algafoodapi.api.util.ApiUtils;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.service.CidadeService;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public ResponseEntity<List<CidadeModel>> listar() {
		var cidadesModel = cidadeModelAssembler.toCollectionModel(cidadeService.listar());
		return ResponseEntity.ok(cidadesModel);
	}
	
	@GetMapping("/{cidadeId}")
	public ResponseEntity<CidadeModel> buscar(@PathVariable Long cidadeId) {
		var cidadeModel = cidadeModelAssembler.toModel(cidadeService.buscar(cidadeId));
		return ResponseEntity.ok(cidadeModel);
	}
	
	@PostMapping
	public ResponseEntity<CidadeModel> adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		var cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
		cidade = cidadeService.adicionar(cidade);
		
		var cidadeModel = cidadeModelAssembler.toModel(cidade);
		
		return ResponseEntity.created(ApiUtils.uri(cidadeModel.getId())).body(cidadeModel);
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody @Valid Cidade cidade) {
		cidade = cidadeService.atualizar(cidadeId, cidade);
		return ResponseEntity.ok(cidade);
	}
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Void> remover(@PathVariable Long cidadeId) {
		cidadeService.remover(cidadeId);
		return ResponseEntity.noContent().build();
	}
}
