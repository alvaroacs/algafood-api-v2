package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.domain.dto.VendaDiaria;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estatisticas", description = "Estatistica das vendas")
public interface EstatisticaControllerOpenAPI {

	@Operation(summary = "Vendas por dia", description = "Consulta das vendas por dia", tags = { "Estatisticas" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na geração do relatório", 
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = VendaDiaria.class)))),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content(schema = @Schema(implementation = Problem.class))) })
	public ResponseEntity<List<VendaDiaria>> consultarVendasDiarias(
			@Parameter(description = "Filtro", required = false, content = {
					@Content(schema = @Schema(implementation = VendaDiariaFilter.class)) }) 
			VendaDiariaFilter filtro,
			@Parameter(description = "Deslocamento de horário a ser considerado na consulta em relação ao UTC", 
				allowEmptyValue = true, required = false, example = "+00:00") String timeOffset);

	@Operation(summary = "Relatório PDF", description = "Relatório em pdf das vendas por dia", tags = {
			"Estatisticas" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso na listagem dos estados", 
					content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE)),
			@ApiResponse(responseCode = "500", description = "Erro Interno no Servidor", 
				content = @Content) })
	public ResponseEntity<byte[]> relatorioVendasDiarias(
			@Parameter(description = "Filtro", required = false, content = {
					@Content(schema = @Schema(implementation = VendaDiariaFilter.class)) }) 
			VendaDiariaFilter filtro,
			@Parameter(description = "Deslocamento de horário a ser considerado na consulta em relação ao UTC", 
				allowEmptyValue = true, required = false, example = "+00:00") String timeOffset);
}
