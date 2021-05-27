package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cidades", description = "Gerencia de cidades")
public interface CidadeControllerOpenapi {

	@Operation(summary = "Listar", description = "Lista todas as cidades", tags = { "Cidades" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem das cidades", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = CidadeModel.class)))),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) })
	public ResponseEntity<List<CidadeModel>> listar();

	@Operation(summary = "Buscar", description = "Busca uma cidade pelo ID", tags = { "Cidades" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso ao buscar de uma cidade",
				content = @Content(schema = @Schema(implementation = CidadeModel.class))),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<CidadeModel> buscar(@Parameter(description = "ID de uma cidade", example = "1") Long cidadeId);

	@Operation(summary = "Cadastrar", description = "Cadastra uma cidade", tags = { "Cidades" })
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cadastra uma cidade", 
				content = @Content(schema = @Schema(implementation = CidadeModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no envio do recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<CidadeModel> adicionar(
			@Parameter(description = "Cidade para adicionar, não pode ser nula ou vazia", required = true,
				content = {@Content(schema = @Schema(implementation = CidadeInput.class))}) 
			CidadeInput cidadeInput);

	@Operation(summary = "Atualizar", description = "Atualiza uma cidade com as novas informações", tags = {
			"Cidades" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cidade Atualizada", 
				content = @Content(schema = @Schema(implementation = CidadeModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<CidadeModel> atualizar(
			@Parameter(description = "ID da cidade que está buscando", example = "1", required = true) Long cidadeId, 
			@Parameter(description = "Cidade com os dados atualizados", required = true, 
				content = { @Content(schema = @Schema(implementation = CidadeInput.class)) }) CidadeInput cidadeInput);

	@Operation(summary = "Remover", description = "Remove uma cidade pelo ID", tags = { "Cidades" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Cidade removida", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> remover(@Parameter(description = "ID da cidade para remoção", example = "1") Long cidadeId);
}
