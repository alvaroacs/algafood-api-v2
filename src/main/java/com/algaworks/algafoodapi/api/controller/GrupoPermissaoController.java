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

import com.algaworks.algafoodapi.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafoodapi.api.model.PermissaoModel;
import com.algaworks.algafoodapi.domain.service.GrupoPermissaoService;

@RestController
@RequestMapping(path = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController {

	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@Autowired
	private GrupoPermissaoService grupoPermissaoService;
	
	@GetMapping
	public ResponseEntity<List<PermissaoModel>> listar(@PathVariable Long grupoId) {
		var permissoes = grupoPermissaoService.listar(grupoId);
		var permissoesModel = permissaoModelAssembler.toCollectionModel(permissoes);
		return ResponseEntity.ok(permissoesModel);
	}
	
	@PutMapping("/{permissaoId}")
	public ResponseEntity<Void> associarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoPermissaoService.associarPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{permissaoId}")
	public ResponseEntity<Void> desassociarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoPermissaoService.desassociarPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
}
