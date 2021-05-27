package com.algaworks.algafood.core.openapi;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder()
				.group("versão 1")
				.packagesToScan("com.algaworks.algafood.api")
				.pathsToMatch("/**")
				.build();
	}
	
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().components(new Components())
				
				.info(new Info().title("AlgaFood API").description("API aberta para clientes e restaurantes")
						.version("1.0.1").contact(new Contact().email("alex.holanda@outlook.com").name("Alex Holanda")
								.url("https://github.com/alex-holanda/")));
	}
}
