package com.algaworks.algafood.api.controller;

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

import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.disassembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.api.util.ApiUtils;
import com.algaworks.algafood.domain.service.EstadoService;

@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;
	
	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public ResponseEntity<List<EstadoModel>> listar() {
		var estadosModel = estadoModelAssembler.toCollectionModel(estadoService.listar());
		return ResponseEntity.ok(estadosModel);
	}

	@GetMapping("/{estadoId}")
	public ResponseEntity<EstadoModel> buscar(@PathVariable Long estadoId) {
		var estadoModel = estadoModelAssembler.toModel(estadoService.buscar(estadoId));
		return ResponseEntity.ok(estadoModel);
	}
	
	@PostMapping
	public ResponseEntity<EstadoModel> adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		var estado = estadoInputDisassembler.toDomainObject(estadoInput);
		estado = estadoService.adicionar(estado);
		
		var estadoModel = estadoModelAssembler.toModel(estado);
		return ResponseEntity.created(ApiUtils.uri(estadoModel.getId())).body(estadoModel);
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<EstadoModel> atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
		var estado = estadoInputDisassembler.toDomainObject(estadoInput);
		estado = estadoService.atualizar(estadoId, estado);
		
		var estadoModel = estadoModelAssembler.toModel(estado);
		return ResponseEntity.ok(estadoModel);
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<Void> remover(@PathVariable Long estadoId) {
		estadoService.remover(estadoId);
		return ResponseEntity.noContent().build();
	}
}
