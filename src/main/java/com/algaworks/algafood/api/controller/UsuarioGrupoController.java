package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.openapi.controller.UsuarioGrupoControllerOpenAPI;
import com.algaworks.algafood.domain.service.UsuarioGrupoService;

@RestController
@RequestMapping(path = "/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenAPI {

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private UsuarioGrupoService usuarioGrupoService;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<GrupoModel>> listar(@PathVariable Long usuarioId) {
		var grupos = usuarioGrupoService.listar(usuarioId);
		var gruposModel = grupoModelAssembler.toCollectionModel(grupos);
		
		gruposModel.removeLinks()
			.add(algaLinks.linkToUsuarioGrupo(usuarioId))
			.add(algaLinks.linkToUsuarioGrupoAssociar(usuarioId, "associar"));
		
		gruposModel.getContent().forEach(grupoModel -> grupoModel.add(
				algaLinks.linkToUsuarioGrupoDesassociar(usuarioId, grupoModel.getId(), "desassociar")));
		
		return ResponseEntity.ok(gruposModel);
	}
	
	@Override
	@PutMapping("/{grupoId}")
	public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioGrupoService.associar(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@DeleteMapping("/{grupoId}")
	public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioGrupoService.desassociar(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}
}
