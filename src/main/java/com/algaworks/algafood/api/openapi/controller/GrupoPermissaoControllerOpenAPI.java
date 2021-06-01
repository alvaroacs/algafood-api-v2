package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PermissaoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface GrupoPermissaoControllerOpenAPI {

	@Operation(summary = "Listar Permissões", description = "Lista as permissões associadas ao grupo", tags = { "Grupos" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Lista de permissões no grupo", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = PermissaoModel.class)))),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<CollectionModel<PermissaoModel>> listar(
			@Parameter(description = "ID do grupo", example = "1") Long grupoId);
	
	@Operation(summary = "Associar Permissão", description = "Associa uma permissão ao grupo", tags = { "Grupos" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associa permissão ao grupo", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> associarPermissao(
			@Parameter(description = "ID do grupo", example = "1") Long grupoId, 
			@Parameter(description = "ID da permissão", example = "1") Long permissaoId);
	
	@Operation(summary = "Desassociar Permissão", description = "Desassocia uma permissão do grupo", tags = { "Grupos" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassocia permissão do grupo", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> desassociarPermissao(
			@Parameter(description = "ID do grupo", example = "1") Long grupoId, 
			@Parameter(description = "ID da permissão", example = "1") Long permissaoId);
}
