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
import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.api.openapi.controller.GrupoPermissaoControllerOpenAPI;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.service.GrupoPermissaoService;

@RestController
@RequestMapping(path = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenAPI {

	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@Autowired
	private GrupoPermissaoService grupoPermissaoService;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Override
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public ResponseEntity<CollectionModel<PermissaoModel>> listar(@PathVariable Long grupoId) {
		var permissoes = grupoPermissaoService.listar(grupoId);
		var permissoesModel = permissaoModelAssembler.toCollectionModel(permissoes);
		
		permissoesModel.removeLinks()
			.add(algaLinks.linkToGrupoPermissoes(grupoId))
			.add(algaLinks.linkToGrupoPermissaoAssociar(grupoId, "associar"));
		
		permissoesModel.getContent().forEach(permissaoModel -> permissaoModel.add(
				algaLinks.linkToGrupoPermissaoDesassociar(grupoId, permissaoModel.getId(), "desassociar")));
		
		return ResponseEntity.ok(permissoesModel);
	}
	
	@Override
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping("/{permissaoId}")
	public ResponseEntity<Void> associarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoPermissaoService.associarPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@DeleteMapping("/{permissaoId}")
	public ResponseEntity<Void> desassociarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoPermissaoService.desassociarPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
}
