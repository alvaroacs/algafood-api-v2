package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Grupos", description = "Gerencia os grupos")
public interface GrupoControllerOpenAPI {
	
	@Operation(summary = "Listar", description = "Lista os grupos", tags = { "Grupos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem dos gruos", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = GrupoModel.class)))),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) 
	})
	public ResponseEntity<List<GrupoModel>> listar();
	
	@Operation(summary = "Buscar", description = "Busca um grupo pelo ID", tags = { "Grupos" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso ao buscar um grupo",
				content = @Content(schema = @Schema(implementation = GrupoModel.class))),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<GrupoModel> buscar(
			@Parameter(description = "ID do grupo", example = "1") Long grupoId);
	
	@Operation(summary = "Cadastrar", description = "Cadastra um grupo", tags = { "Grupos" })
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cadastra um grupo", 
				content = @Content(schema = @Schema(implementation = GrupoModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no envio do recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<GrupoModel> adicionar(
			@Parameter(description = "Grupo com os dados atualizados", required = true, 
				content = { @Content(schema = @Schema(implementation = GrupoInput.class)) })
			GrupoInput grupoInput);
	
	@Operation(summary = "Atualizar", description = "Atualiza o grupo", tags = { "Grupos" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Grupo atualizada", 
				content = @Content(schema = @Schema(implementation = GrupoModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<GrupoModel> atualizar(
			@Parameter(description = "ID do grupo", example = "1") Long grupoId, 
			@Parameter(description = "Grupo com os dados atualizados", required = true, 
				content = { @Content(schema = @Schema(implementation = GrupoInput.class)) })
			GrupoInput grupoInput);
	
	@Operation(summary = "Remover", description = "", tags = { "Grupos" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Grupo removido", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Grupo não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> remover(
			@Parameter(description = "ID do grupo", example = "1") Long grupoId);
}
