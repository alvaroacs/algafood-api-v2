package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Formas de Pagamento", description = "Gerencia as formas de pagamento")
public interface FormaPagamentoControllerOpenAPI {

	@Operation(summary = "Listar", description = "Lista as formas de pagamento", tags = { "Formas de Pagamento" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem das formas de pagamento", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = FormaPagamentoModel.class)))),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) })
	public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);

	@Operation(summary = "Buscar", description = "Busca uma forma de pagamento pelo ID", tags = { "Formas de Pagamento" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso ao buscar uma forma de pagamento",
				content = @Content(schema = @Schema(implementation = FormaPagamentoModel.class))),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<FormaPagamentoModel> buscar(
			@Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId);

	@Operation(summary = "Cadastrar", description = "Cadastra uma forma de pagamento", tags = { "Formas de Pagamento" })
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cadastra uma forma de pagamento", 
				content = @Content(schema = @Schema(implementation = FormaPagamentoModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no envio do recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<FormaPagamentoModel> adicionar(
			@Parameter(description = "Forma de pagamento com os dados para cadastro", required = true, 
				content = { @Content(schema = @Schema(implementation = FormaPagamentoInput.class)) })
			FormaPagamentoInput formaPagamentoInput);

	@Operation(summary = "Atualizar", description = "Atualiza uma forma de pagmaento", tags = { "Formas de Pagamento" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada", 
				content = @Content(schema = @Schema(implementation = FormaPagamentoModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<FormaPagamentoModel> atualizar(
			@Parameter(description = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId,
			@Parameter(description = "Forma de pagamento com os dados atualizados", required = true, 
				content = { @Content(schema = @Schema(implementation = FormaPagamentoInput.class)) })
			FormaPagamentoInput formaPagamentoInput);

	@Operation(summary = "Remover", description = "Remove uma forma de pagamento pelo ID", tags = { "Formas de Pagamento" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Forma de pagamento removida", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<FormaPagamentoModel> remover(
			@Parameter(description = "ID de forma de pagamento", example = "1") Long formaPagamentoId);
}
