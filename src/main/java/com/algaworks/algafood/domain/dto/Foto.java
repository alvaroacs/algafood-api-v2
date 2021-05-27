package com.algaworks.algafood.domain.dto;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Foto {

	private String nomeArquivo;
	private Long tamanho;
	private String contentType;
	private InputStream inputStream;
	private String url;
	
	public boolean temUrl() {
		return getUrl() != null;
	}
}
