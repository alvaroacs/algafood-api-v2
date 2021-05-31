package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estados", description = "Gerencia de Estados")
public interface EstadoControllerOpenAPI {

	@Operation(summary = "Listar", description = "Lista todas os estados", tags = { "Estados" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem dos estados", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = CidadeModel.class)))),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) })
	public ResponseEntity<CollectionModel<EstadoModel>> listar();

	@Operation(summary = "Buscar", description = "Busca um estado pelo ID", tags = { "Estados" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso ao buscar de um estado",
				content = @Content(schema = @Schema(implementation = CidadeModel.class))),
		@ApiResponse(responseCode = "404", description = "Estado não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<EstadoModel> buscar(@Parameter(description = "ID de um estado", example = "1") Long estadoId);

	@Operation(summary = "Cadastrar", description = "Cadastra um estado", tags = { "Estados" })
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cadastra um estado", 
				content = @Content(schema = @Schema(implementation = CidadeModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no envio do recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<EstadoModel> adicionar(
			@Parameter(description = "Estado para adicionar, não pode ser nulo ou vazio", required = true, 
				content = { @Content(schema = @Schema(implementation =  EstadoInput.class)) })
			EstadoInput estadoInput);

	@Operation(summary = "Atualizar", description = "Atualiza um estado com as novas informações", tags = { "Estados" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Estado Atualizado", 
				content = @Content(schema = @Schema(implementation = CidadeModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Estado não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<EstadoModel> atualizar(
			@Parameter(description = "ID do estado", example = "1", required = true) 
			Long estadoId,
			@Parameter(description = "")
			EstadoInput estadoInput);

	@Operation(summary = "Remover", description = "Remove um estado pelo ID", tags = { "Estados" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Estado removida", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> remover(
			@Parameter(description = "ID do estado", example = "1", required = true) Long estadoId);
}
