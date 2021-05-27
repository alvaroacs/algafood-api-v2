package com.algaworks.algafood.api.exceptionhandler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProblemObject {

	@Schema(description = "object name", example = "descricao")
	private String name;
	
	@Schema(description = "default user message", example = "Descrição é obrigatória")
	private String userMessage;
}
