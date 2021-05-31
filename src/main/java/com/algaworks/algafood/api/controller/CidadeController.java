package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.disassembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.api.openapi.controller.CidadeControllerOpenAPI;
import com.algaworks.algafood.api.util.ApiUtils;
import com.algaworks.algafood.domain.service.CidadeService;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenAPI {

	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<CidadeModel>> listar() {
		var cidadesModel = cidadeModelAssembler.toCollectionModel(cidadeService.listar());
		return ResponseEntity.ok(cidadesModel);
	}
	
	@Override
	@GetMapping("/{cidadeId}")
	public ResponseEntity<CidadeModel> buscar(@PathVariable Long cidadeId) {
		var cidadeModel = cidadeModelAssembler.toModel(cidadeService.buscar(cidadeId));
		return ResponseEntity.ok(cidadeModel);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<CidadeModel> adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		var cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
		cidade = cidadeService.adicionar(cidade);
		
		var cidadeModel = cidadeModelAssembler.toModel(cidade);
		
		return ResponseEntity.created(ApiUtils.uri(cidadeModel.getId())).body(cidadeModel);
	}
	
	@Override
	@PutMapping("/{cidadeId}")
	public ResponseEntity<CidadeModel> atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {
		var cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
		cidade = cidadeService.atualizar(cidadeId, cidade);
		var cidadeModel = cidadeModelAssembler.toModel(cidade);
		return ResponseEntity.ok(cidadeModel);
	}
	
	@Override
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Void> remover(@PathVariable Long cidadeId) {
		cidadeService.remover(cidadeId);
		return ResponseEntity.noContent().build();
	}
}
