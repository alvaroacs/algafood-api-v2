package com.algaworks.algafood.infrastructure.service.storage;

import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.dto.Foto;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3StoreService implements FotoStorageService {

	@Autowired
	private AmazonS3 amazonS3;

	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(Foto novaFoto) {
		try {
			var caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());

			var objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(novaFoto.getTamanho());
			objectMetadata.setContentType(novaFoto.getContentType());

			var putObjectRequest = new PutObjectRequest(storageProperties.getS3().getBucket(), caminhoArquivo,
					novaFoto.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);

			amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Não foi possível enviar arquivo para Amazon S3", e);
		}
	}

	@Override
	public void remover(String nomeArquivo) {
		try {
			var deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(),
					getCaminhoArquivo(nomeArquivo));
			amazonS3.deleteObject(deleteObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Não possível remover o arquivo do Amazon S3", e);
		}
	}

	@Override
	public Foto.FotoBuilder recuperar(String nomeArquivo) {
		try {
			var caminhoArquivo = getCaminhoArquivo(nomeArquivo);
			var url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);

			return Foto.builder().url(url.toString());
		} catch (Exception e) {
			throw new StorageException("Não foi possível recuperar o arquivo do Amazon S3", e);
		}
	}

	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorio(), nomeArquivo);
	}
}
