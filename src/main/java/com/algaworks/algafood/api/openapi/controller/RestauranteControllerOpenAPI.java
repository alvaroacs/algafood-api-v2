package com.algaworks.algafood.api.openapi.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurantes", description = "Gerencia de restaurantes")
public interface RestauranteControllerOpenAPI {

	@Operation(summary = "Listar", description = "Lista os restaurantes", tags = { "Restaurantes" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem dos restaurantes", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestauranteModel.class)))),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) 
	})
	public ResponseEntity<List<RestauranteModel>> listar();

	@Operation(summary = "Lista personalizada", description = "Lista os restaurantes", tags = { "Restaurantes" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem dos restaurantes", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestauranteModel.class)))),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) 
	})
	public ResponseEntity<List<RestauranteModel>> listarPersonalizado(
			@Parameter(description = "Nome do restaurante", example = "Tuk Tuk") String nome, 
			@Parameter(description = "Taxa frete - valor inicial", example = "1") BigDecimal taxaFreteInicial,
			@Parameter(description = "Taxa frete - valor final", example = "10") BigDecimal taxaFreteFinal);

	@Operation(summary = "Buscar", description = "Busca um restaurante pelo ID", tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso ao buscar um restaurante",
				content = @Content(schema = @Schema(implementation = RestauranteModel.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<RestauranteModel> buscar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);

	@Operation(summary = "Cadastrar", description = "Cadastra um restaurante", tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Restaurante cadastrado", 
				content = @Content(schema = @Schema(implementation = RestauranteModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no envio do recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<RestauranteModel> adicionar(
			@Parameter(description = "Restaurante a ser cadastrado, não pode ser nulo ou vazio", required = true,
					content = { @Content(schema = @Schema(implementation = RestauranteInput.class)) }) 
			RestauranteInput restauranteInput);

	@Operation(summary = "Atualizar", description = "Atualiza uma permissão com as novas informações", tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Restaurante Atualizado", 
				content = @Content(schema = @Schema(implementation = RestauranteModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<RestauranteModel> atualizar(
			@Parameter(description = "ID do restaurante", example = "1", required = true)
			Long restauranteId,
			@Parameter(description = "Restaurante a ser atualizado, não pode ser nulo ou vazio", required = true,
				content = { @Content(schema = @Schema(implementation = RestauranteInput.class)) }) 
			RestauranteInput restauranteInput);
	
	@Operation(summary = "Ativar", description = "Ativa um restaurante", tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante ativo", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> ativar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	@Operation(summary = "Ativar Multiplos", description = "Ativa multiplos restaurantes", tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurantes ativos", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> ativar(
			@Parameter(description = "IDs dos restaurantes para serem ativados", example = "[1,2,3,4]")
			List<Long> restaurantesId);
	
	@Operation(summary = "Inativar", description = "Inativa um restaurante", tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante inativo", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> inativar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	@Operation(summary = "Inativar Multiplos", description = "Inativa multiplos restaurantes", tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurantes ativos", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> inativar(
			@Parameter(description = "IDs dos restaurantes para serem inativados", example = "[1,2,3,4]")
			List<Long> restaurantesId);
	
	@Operation(summary = "Abrir", description = "Abrir restaurante", tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Abre o restaurante", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> abrir(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	@Operation(summary = "Fechar", description = "Fecha o restaurante", tags = { "Restaurantes" })
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Fechar restaurante", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> fechar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
}
