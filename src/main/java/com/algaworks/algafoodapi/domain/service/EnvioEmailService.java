package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.dto.Mensagem;

public interface EnvioEmailService {

	void enviar(Mensagem mensagem);
	
}
