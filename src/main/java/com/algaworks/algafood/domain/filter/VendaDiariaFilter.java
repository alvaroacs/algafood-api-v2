package com.algaworks.algafood.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDiariaFilter {

	@Schema(description = "ID do restaurante", example = "1")
	private Long restauranteId;
	
	@Schema(description = "Data de criação inicial", example = "2020-05-27T20:05:29.645Z")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;
	
	@Schema(description = "Data de criação inicial", example = "2021-05-27T20:05:29.645Z")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;
}
