package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.disassembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.api.openapi.controller.CozinhaControllerOpenAPI;
import com.algaworks.algafood.api.util.ApiUtils;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping(path = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenAPI {
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;
	
	@Autowired
	private CozinhaService cozinhaService;

	@Override
	@GetMapping
	public ResponseEntity<PagedModel<CozinhaModel>> listar(Pageable pageable) {
		var cozinhasPage = cozinhaService.listar(pageable);
		
		var pagedModel = pagedResourcesAssembler.toModel(cozinhasPage, cozinhaModelAssembler);
		
		return ResponseEntity.ok(pagedModel);
	}
	
	@Override
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<CozinhaModel> buscar(@PathVariable Long cozinhaId) {
		var cozinhaModel = cozinhaModelAssembler.toModel(cozinhaService.buscar(cozinhaId));
		return ResponseEntity.ok(cozinhaModel);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<CozinhaModel> adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		var cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		cozinha = cozinhaService.adicionar(cozinha);
		
		var cozinhaModel = cozinhaModelAssembler.toModel(cozinha);
		return ResponseEntity.created(ApiUtils.uri(cozinhaModel.getId())).body(cozinhaModel);
	}
	
	@Override
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<CozinhaModel> atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
		var cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
		cozinha = cozinhaService.atualizar(cozinhaId, cozinha);
		
		var cozinhaModel = cozinhaModelAssembler.toModel(cozinha);
		return ResponseEntity.ok(cozinhaModel);
	}
	
	@Override
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Void> remover(@PathVariable Long cozinhaId) {
		cozinhaService.remover(cozinhaId);
		return ResponseEntity.noContent().build();
	}
}
