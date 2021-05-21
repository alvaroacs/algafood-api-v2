package com.algaworks.algafoodapi.core.storage;

import com.amazonaws.regions.Regions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class S3Store {

	private String idChaveAcesso;
	
	private String chaveAcessoSecreta;
	
	private String bucket;
	
	private Regions regiao;
	
	private String diretorio;
}
