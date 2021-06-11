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

import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.disassembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.api.openapi.controller.GrupoControllerOpenAPI;
import com.algaworks.algafood.api.util.ApiUtils;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenAPI {
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	@Autowired
	private GrupoService grupoService;

	@Override
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public ResponseEntity<CollectionModel<GrupoModel>> listar() {
		var gruposModel = grupoModelAssembler.toCollectionModel(grupoService.listar());
		return ResponseEntity.ok(gruposModel);
	}
	
	@Override
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping("/{grupoId}")
	public ResponseEntity<GrupoModel> buscar(@PathVariable Long grupoId) {
		var grupoModel = grupoModelAssembler.toModel(grupoService.buscar(grupoId));
		return ResponseEntity.ok(grupoModel);
	}
	
	@Override
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PostMapping
	public ResponseEntity<GrupoModel> adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		var grupo = grupoService.adicionar(grupoInputDisassembler.toDomainObject(grupoInput));
		var grupoModel = grupoModelAssembler.toModel(grupo);
		return ResponseEntity.created(ApiUtils.uri(grupoModel.getId())).body(grupoModel);
	}
	
	@Override
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping("/{grupoId}")
	public ResponseEntity<GrupoModel> atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
		var grupo = grupoInputDisassembler.toDomainObject(grupoInput);
		var grupoModel = grupoModelAssembler.toModel(grupoService.atualizar(grupoId, grupo));
		return ResponseEntity.ok(grupoModel);
	}
	
	@Override
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@DeleteMapping("/{grupoId}")
	public ResponseEntity<Void> remover(@PathVariable Long grupoId) {
		grupoService.remover(grupoId);
		return ResponseEntity.noContent().build();
	}
}
