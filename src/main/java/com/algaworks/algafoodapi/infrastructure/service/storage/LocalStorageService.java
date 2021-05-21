package com.algaworks.algafoodapi.infrastructure.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.algaworks.algafoodapi.core.storage.StorageProperties;
import com.algaworks.algafoodapi.domain.dto.Foto;
import com.algaworks.algafoodapi.domain.service.FotoStorageService;

public class LocalStorageService implements FotoStorageService {

	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(Foto novaFoto) {
		try {
			var arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());

			FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (IOException e) {
			throw new StorageException("Não foi possível armazenar o arquivo.", e);
		}
	}

	@Override
	public void remover(String nomeArquivo) {
		try {
			var arquivoPath = getArquivoPath(nomeArquivo);
			Files.deleteIfExists(arquivoPath);
		} catch (IOException e) {
			throw new StorageException("Não foi possível remover o arquivo armazenado.", e);
		}
	}

	@Override
	public Foto.FotoBuilder recuperar(String nomeArquivo) {
		try {
			var arquivoPath = getArquivoPath(nomeArquivo);

			return Foto.builder().inputStream(Files.newInputStream(arquivoPath));
		} catch (IOException e) {
			throw new StorageException("Não foi possível recuperar o arquivo armazenado", e);
		}
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return storageProperties.getLocal().getDiretorio().resolve(Path.of(nomeArquivo));
	}

}
