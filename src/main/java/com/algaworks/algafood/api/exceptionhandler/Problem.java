package com.algaworks.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	@Schema(description = "Status code", example = "000")
	private Integer status;
	
	@Schema(description = "URL to the problem page", example = "https://algafood.com.br/recurso-nao-encontrado")
	private String type;
	
	@Schema(description = "Problem title", example =  "Recurso não encontrado")
	private String title;
	
	@Schema(description = "Detail message", example = "Não existe um cadastro do recurso com código 1")
	private String detail;
	
	@Schema(description = "User message problem description", example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
	private String userMessage;
	
	@Schema(description = "Date that the problem occurred", example = "2021-05-26T22:15:45.3339422Z")
	private OffsetDateTime timestamp;
	
	@ArraySchema(schema = @Schema(implementation = ProblemObject.class, required = false, description = "Used for form fields error"))
	private List<ProblemObject> objects; 
}
