package com.algaworks.algafood.api.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Fluxo do Pedido", description = "Fluxo do negócio de pedido")
public interface FluxoPedidoControllerOpenAPI {

	@Operation(summary = "Confirmar", description = "Restaurante confirma um pedido", tags = { "Fluxo do Pedido" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido confirmado", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrada", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> confirmar(
			@Parameter(description = "Código do pedido", example = "b5741512-8fbc-47fa-9ac1-b530354fc0ff", required = true)
			String codigoPedido);
	
	@Operation(summary = "Entregar", description = "Restaurante confirma a entrega do pedido", tags = { "Fluxo do Pedido" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido entregue", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrada", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> entregar(
			@Parameter(description = "Código do pedido", example = "b5741512-8fbc-47fa-9ac1-b530354fc0ff", required = true)
			String codigoPedido);
	
	@Operation(summary = "Cancelar", description = "Restaurante ou cliente cancela um pedido quando criado", tags = { "Fluxo do Pedido" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido cancelado", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrada", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> cancelar(
			@Parameter(description = "Código do pedido", example = "b5741512-8fbc-47fa-9ac1-b530354fc0ff", required = true)
			String codigoPedido);
}
