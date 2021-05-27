package com.algaworks.algafood.api.controller;

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

import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.domain.service.RestauranteResponsavelService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteResponsavelController {

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private RestauranteResponsavelService restauranteResponsavelService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> listar(@PathVariable Long restauranteId) {
		var usuarios = restauranteResponsavelService.listar(restauranteId);
		var usuariosModel = usuarioModelAssembler.toCollectionModel(usuarios);
		return ResponseEntity.ok(usuariosModel);
	}
	
	@PutMapping("/{responsavelId}")
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long responsavelId) {
		restauranteResponsavelService.associar(restauranteId, responsavelId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{responsavelId}")
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long responsavelId) {
		restauranteResponsavelService.desassociar(restauranteId, responsavelId);
		return ResponseEntity.noContent().build();
	}
}
