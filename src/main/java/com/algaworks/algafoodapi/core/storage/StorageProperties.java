package com.algaworks.algafoodapi.core.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {

	private LocalStore local = new LocalStore();
	
	private TipoStorage tipo = TipoStorage.LOCAL;

	private S3Store s3 = new S3Store();
}
