package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.api.model.input.PermissaoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Permissoes", description = "Gerencia as Permissoes")
public interface PermissaoControllerOpenAPI {

	@Operation(summary = "Listar", description = "Lista as Permissoes", tags = { "Permissoes" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem das Permissoes", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = PermissaoModel.class)))),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) 
	})
	public ResponseEntity<List<PermissaoModel>> listar();
	
	@Operation(summary = "Buscar", description = "Busca uma permissão pelo ID", tags = { "Permissoes" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso ao buscar uma permissão",
				content = @Content(schema = @Schema(implementation = PermissaoModel.class))),
		@ApiResponse(responseCode = "404", description = "Permissão não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<PermissaoModel> buscar(
			@Parameter(description = "ID de uma permissão", example = "1") Long permissaoId);
	
	@Operation(summary = "Cadastrar", description = "Cadastra uma permissão", tags = { "Permissoes" })
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Permissão cadastrada", 
				content = @Content(schema = @Schema(implementation = PermissaoModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no envio do recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<PermissaoModel> adicionar(
			@Parameter(description = "Permissão para adicionar, não pode ser nula ou vazia", required = true,
					content = { @Content(schema = @Schema(implementation = PermissaoInput.class)) })
			PermissaoInput permissaoInput);
	
	@Operation(summary = "Atualizar", description = "Atualiza uma permissão com as novas informações", tags = { "Permissoes" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Permissão atualizada", 
				content = @Content(schema = @Schema(implementation = PermissaoModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<PermissaoModel> atualizar(
			@Parameter(description = "ID de uma permissão", example = "1")
			Long permissaoId, 
			@Parameter(description = "Permissão para adicionar, não pode ser nula ou vazia", required = true,
				content = { @Content(schema = @Schema(implementation = PermissaoInput.class)) })
			PermissaoInput permissaoInput);
	
	@Operation(summary = "Remover", description = "Remove uma permissão pelo ID", tags = { "Permissoes" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Permissão removida", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> remover(
			@Parameter(description = "ID de uma permissão", example = "1") Long permissaoId);
}
