package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.input.ProdutoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos", description = "Gerencia de produtos")
public interface RestauranteProdutoControllerOpenAPI {

	@Operation(summary = "Listar", description = "Lista os produtos de um restaurante", tags = { "Produtos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem dos restaurantes", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProdutoModel.class)))),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) 
	})
	public ResponseEntity<CollectionModel<ProdutoModel>> listar(
			@Parameter(description = "ID do restaurante", example = "1", required = true)
			Long restauranteId,
			@Parameter(description = "Incluir produtos inativos", example = "false", required = false)
			Boolean incluirInativos);

	@Operation(summary = "Buscar", description = "Busca o produto de um restaurante", tags = { "Produtos" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso ao buscar um produto",
				content = @Content(schema = @Schema(implementation = ProdutoModel.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<ProdutoModel> buscar(
			@Parameter(description = "ID do restaurante", example = "1", required = true)
			Long restauranteId, 
			@Parameter(description = "ID do produto", example = "1", required = true)
			Long produtoId);

	@Operation(summary = "Cadastrar", description = "Cadastra um produto", tags = { "Produtos" })
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Produto cadastrado", 
				content = @Content(schema = @Schema(implementation = ProdutoModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no envio do recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<ProdutoModel> adicionar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
			@Parameter(description = "Produto a ser cadastrado, não pode ser nulo ou vazio", required = true,
				content = { @Content(schema = @Schema(implementation = ProdutoInput.class)) })
			ProdutoInput produtoInput);

	@Operation(summary = "Atualizar", description = "Atualiza um produto com as novas informações", tags = { "Produtos" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Produto atualizado", 
				content = @Content(schema = @Schema(implementation = ProdutoModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<ProdutoModel> atualizar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do produto", example = "1", required = true) Long produtoId,
			@Parameter(description = "Produto a ser atualizado", required = true,
				content = { @Content(schema = @Schema(implementation = ProdutoInput.class)) })
			ProdutoInput produtoInput);
}
