package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cozinhas", description = "Gerencia de cozinhas")
public interface CozinhaControllerOpenAPI {
	
	//TODO: corrigir a resposta e o parametro
	@Operation(summary = "Listar", description = "Lista todas as cozinhas", tags = { "Cozinhas" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso na listagem de cozinhas",
				content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Page<CozinhaModel>> listar(
			@Parameter(required = false, allowEmptyValue = true, 
				content = @Content(schema = @Schema(implementation = Pageable.class))) 
			Pageable pageable);
	
	@Operation(summary = "Buscar", description = "Busca uma cozinha pelo ID", tags = { "Cozinhas" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso ao buscar de uma cozinha",
				content = @Content(schema = @Schema(implementation = CozinhaModel.class))),
		@ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<CozinhaModel> buscar(
			@Parameter(description = "ID da cozinha", example = "1", required = true) 
			Long cozinhaId);
	
	@Operation(summary = "Cadastrar", description = "Cadastra uma cozinha", tags = { "Cozinhas" })
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cadastra uma cozinha", 
				content = @Content(schema = @Schema(implementation = CozinhaModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no envio do recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<CozinhaModel> adicionar(
			@Parameter(description = "Cozinha para adicionar, não pode ser nula ou vazia", required = true,
				content = {@Content(schema = @Schema(implementation = CozinhaInput.class))})
			CozinhaInput cozinhaInput);

	@Operation(summary = "Atualizar", description = "Atualiza uma cozinha", tags = { "Cozinhas" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cozinha Atualizada", 
				content = @Content(schema = @Schema(implementation = CozinhaModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Cozinha não encontrada", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<CozinhaModel> atualizar(
			@Parameter(description = "ID da cozinha", example = "1", required = true) Long cozinhaId, 
			@Parameter(description = "Cozinha com os dados atualizados", required = true, 
				content = { @Content(schema = @Schema(implementation = CozinhaInput.class)) })
			CozinhaInput cozinhaInput);
	
	@Operation(summary = "Remover", description = "Remove uma cozinha pelo ID", tags = { "Cozinhas" })
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
	public ResponseEntity<Void> remover(
			@Parameter(description = "ID da cozinha", example = "1", required = true)
			Long cozinhaId);
}
