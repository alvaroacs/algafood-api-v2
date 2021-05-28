package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface UsuarioGrupoControllerOpenAPI {

	@Operation(summary = "Listar grupos", description = "Lista os grupos", tags = { "Usuarios" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem dos grupos", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = GrupoModel.class)))),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) 
	})
	public ResponseEntity<List<GrupoModel>> listar(
			@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId);
	
	@Operation(summary = "Associar grupo", description = "Associa um usuário a um grupo", tags = { "Usuarios" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associa ao grupo", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> associar(
			@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId, 
			@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);
	
	@Operation(summary = "Desassociar grupo", description = "Desassocia um usuário de um grupo", tags = { "Usuarios" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassocia o grupo", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> desassociar(
			@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId, 
			@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);
}
