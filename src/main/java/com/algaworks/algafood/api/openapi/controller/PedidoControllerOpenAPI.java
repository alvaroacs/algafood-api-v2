package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos", description = "Gerencia de pedidos")
public interface PedidoControllerOpenAPI {

	@Operation(summary = "Pesquisar", description = "Pesquisa pedidos", tags = { "Pedidos" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso na pesquisa de pedidos",
				content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<PagedModel<PedidoResumoModel>> pesquisar(
			@Parameter(description = "Filtro de pesquisa do pedido", 
				content = { @Content(schema =  @Schema(implementation = PedidoFilter.class)) })
			PedidoFilter pedidoFilter, 
			@Parameter(description = "Paginação", required = false)
			Pageable pageable);
	
	@Operation(summary = "Buscar", description = "Busca um pedido pelo código", tags = { "Pedidos" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso ao buscar o pedido",
				content = @Content(schema = @Schema(implementation = PedidoModel.class))),
		@ApiResponse(responseCode = "404", description = "Pedido não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<PedidoModel> buscar(
			@Parameter(description = "Código do pedido", example = "02cd99ae-9f7f-4550-bfe0-b63bd7de979d")
			String pedidoCodigo);
	
	@Operation(summary = "Emitir", description = "Emitir pedido", tags = { "Pedidos" })
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Pedido emitido", 
				content = @Content(schema = @Schema(implementation = PedidoModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no envio do recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<PedidoModel> emitir(
			@Parameter(description = "Pedido a ser emitido", required = true, 
				content = @Content(schema = @Schema(implementation = PedidoInput.class))) 
			PedidoInput pedidoInput);
}
