package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios", description = "Gerencia de usuarios")
public interface UsuarioControllerOpenAPI {
	
	@Operation(summary = "Listar", description = "Lista os usuários", tags = { "Usuarios" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem de usuários", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioModel.class)))),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) 
	})
	public ResponseEntity<List<UsuarioModel>> listar();

	@Operation(summary = "Buscar", description = "Busca um usuário pelo ID", tags = { "Usuarios" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso ao buscar um usuário",
				content = @Content(schema = @Schema(implementation = UsuarioModel.class))),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<UsuarioModel> buscar(
			@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId);
	
	@Operation(summary = "Cadastrar", description = "Cadastra um usuário", tags = { "Usuarios" })
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Usuário cadastrado", 
				content = @Content(schema = @Schema(implementation = UsuarioModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no envio do recurso",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<UsuarioModel> adicionar(
			@Parameter(description = "Usuário para adicionar, não pode ser nula ou vazia", required = true,
				content = {@Content(schema = @Schema(implementation = UsuarioComSenhaInput.class))})
			UsuarioComSenhaInput usuarioComSenhaInput);
	
	@Operation(summary = "Atualizar", description = "Atualiza um usuário com as novas informações", tags = { "Usuarios" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Usuário atualizada", 
				content = @Content(schema = @Schema(implementation = UsuarioModel.class))),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<UsuarioModel> atualizar(
			@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId, 
			@Parameter(description = "Usuário com os dados atualizados", required = true,
					content = { @Content(schema = @Schema(implementation = UsuarioInput.class)) }) 
			UsuarioInput usuarioInput);
	
	@Operation(summary = "Alterar senha", description = "Altera a senha do usuário", tags = { "Usuarios" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Senha alterada", 
				content = @Content),
		@ApiResponse(responseCode = "400", description = "Problema no recurso", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> alterarSenha(
			@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId, 
			@Parameter(content = { @Content(schema = @Schema(implementation = SenhaInput.class)) })
			SenhaInput senhaInput);
}
