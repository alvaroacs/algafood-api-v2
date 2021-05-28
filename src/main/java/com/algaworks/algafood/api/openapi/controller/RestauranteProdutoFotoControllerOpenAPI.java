package com.algaworks.algafood.api.openapi.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface RestauranteProdutoFotoControllerOpenAPI {

	@Operation(summary = "Buscar a foto", description = "Busca a foto", tags = { "Produtos" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso ao buscar a foto do produto",
				content = {  @Content(schema = @Schema(implementation = FotoProdutoModel.class), 
					mediaType = MediaType.APPLICATION_JSON_VALUE) }),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<FotoProdutoModel> buscar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);
	
	@Operation(tags = { "Produtos" }, hidden = true)
	public ResponseEntity<?> servir(Long restauranteId, Long produtoId,
			String acceptHeader) throws HttpMediaTypeNotAcceptableException;

	@Operation(summary = "Atualiza a foto", description = "Atualiza a foto de um produto", tags = { "Produtos" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Foto atualizado", 
				content = @Content(schema = @Schema(implementation = FotoProdutoModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<FotoProdutoModel> atualizarFoto(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
			@Parameter(description = "ID do produto", example = "1", required = true) Long produtoId, 
			@Parameter(description = "Conteúdo do formulário", required = true,
					content = { @Content(schema = @Schema(implementation = FotoProdutoInput.class)) }) 
			FotoProdutoInput fotoProdutoInput) throws IOException;

	@Operation(summary = "Remover a foto", description = "Remove a foto do produto", tags = { "Produtos" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Foto removida", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> remover(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);

}
