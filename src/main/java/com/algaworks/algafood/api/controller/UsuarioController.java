package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.disassembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.api.openapi.controller.UsuarioControllerOpenAPI;
import com.algaworks.algafood.api.util.ApiUtils;
import com.algaworks.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenAPI {
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> listar() {
		var usuariosModel = usuarioModelAssembler.toCollectionModel(usuarioService.listar());
		return ResponseEntity.ok(usuariosModel);
	}

	@Override
	@GetMapping("/{usuarioId}")
	public ResponseEntity<UsuarioModel> buscar(@PathVariable Long usuarioId) {
		var usuarioModel = usuarioModelAssembler.toModel(usuarioService.buscar(usuarioId));
		return ResponseEntity.ok(usuarioModel);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<UsuarioModel> adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput) {
		var usuario = usuarioInputDisassembler.toDomainObject(usuarioComSenhaInput);
		usuario = usuarioService.adicionar(usuario);
		var usuarioModel = usuarioModelAssembler.toModel(usuario);
		return ResponseEntity.created(ApiUtils.uri(usuarioModel.getId())).body(usuarioModel);
	}
	
	@Override
	@PutMapping("/{usuarioId}")
	public ResponseEntity<UsuarioModel> atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {
		var usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		usuario = usuarioService.atualizar(usuarioId, usuario);
		var usuarioModel = usuarioModelAssembler.toModel(usuario);
		return ResponseEntity.ok(usuarioModel);
	}
	
	@Override
	@PutMapping("/{usuarioId}/senha")
	public ResponseEntity<Void> alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput) {
		usuarioService.alterarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
		return ResponseEntity.noContent().build();
	}
}
