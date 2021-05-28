package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface RestauranteResponsavelControllerOpenAPI {

	@Operation(summary = "Listar usuários", description = "Lista os usuários do restaurante", tags = { "Restaurantes" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem dos usuários", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioModel.class)))),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) 
	})
	public ResponseEntity<List<UsuarioModel>> listar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	@Operation(summary = "Associar um usuário", description = "Associa um usuário ao restaurante", tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associa um usuário ao restaurante", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> associar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do usuário", example = "1", required = true) Long responsavelId);
	
	@Operation(summary = "Desassociar um usuário", description = "Desassocia um usuário ao restaurante", tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassociar um usuário do restaurante", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> desassociar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do usuário", example = "1", required = true) Long responsavelId);
}
