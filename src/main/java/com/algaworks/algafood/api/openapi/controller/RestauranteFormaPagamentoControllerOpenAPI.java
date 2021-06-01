package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface RestauranteFormaPagamentoControllerOpenAPI {

	@Operation(summary = "Listar formas de pagamento", description = "Lista as formas de pagamento associadas", tags = { "Restaurantes" })
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Sucesso na listagem das formas de pagamento", 
			content = @Content(array = @ArraySchema(schema = @Schema(implementation = FormaPagamentoModel.class)))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
			content = @Content(schema = @Schema(implementation = Problem.class))) 
	})
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(
			@Parameter(description = "ID do restaurante", example = "1", required = true)
			Long restauranteId);
	
	@Operation(summary = "Associar forma de pagamento", description = "Associa uma forma de pagamento ao restaurante", 
				tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associa forma de pagamento ao restaurante", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> associarFormaPagamento(
			@Parameter(description = "ID do restaurante", example = "1", required = true)
			Long restauranteId, 
			@Parameter(description = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId);
	
	@Operation(summary = "Associar forma de pagamento", description = "Associa uma forma de pagamento ao restaurante", 
				tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassociar forma de pagamento do restaurante", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> desassociarFormaPagamento(
			@Parameter(description = "ID do restaurante", example = "1", required = true)
			Long restauranteId, 
			@Parameter(description = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId);
}
