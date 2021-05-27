package com.algaworks.algafood.domain.service;

import java.util.UUID;

import com.algaworks.algafood.domain.dto.Foto;

public interface FotoStorageService {

	void armazenar(Foto novaFoto);
	
	void remover(String nomeArquivo);
	
	Foto.FotoBuilder recuperar(String nomeArquivo);
	
	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString().concat("_").concat(nomeOriginal);
	}
	
	default void substituir(String nomeArquivoAntigo, Foto novaFoto) {
		armazenar(novaFoto);
		
		if (nomeArquivoAntigo != null) {
			remover(nomeArquivoAntigo);
		}
	}
}
