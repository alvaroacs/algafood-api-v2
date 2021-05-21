package com.algaworks.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoodapi.api.assembler.GrupoModelAssembler;
import com.algaworks.algafoodapi.api.model.GrupoModel;
import com.algaworks.algafoodapi.domain.service.UsuarioGrupoService;

@RestController
@RequestMapping(path = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private UsuarioGrupoService usuarioGrupoService;
	
	@GetMapping
	public ResponseEntity<List<GrupoModel>> listar(@PathVariable Long usuarioId) {
		var grupos = usuarioGrupoService.listar(usuarioId);
		var gruposModel = grupoModelAssembler.toCollectionModel(grupos);
		return ResponseEntity.ok(gruposModel);
	}
	
	@PutMapping("/{grupoId}")
	public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioGrupoService.associar(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{grupoId}")
	public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioGrupoService.desassociar(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}
}
