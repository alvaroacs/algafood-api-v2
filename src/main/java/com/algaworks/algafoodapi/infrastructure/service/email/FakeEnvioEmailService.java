package com.algaworks.algafoodapi.infrastructure.service.email;

import com.algaworks.algafoodapi.domain.dto.Mensagem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService extends SmtpEmailService {

	@Override
	public void enviar(Mensagem mensagem) {
		var corpo = processarTemplate(mensagem);
		
		log.info("[FAKE EMAIL SERVICE] ", mensagem.getDestinatarios(), corpo);
	}
}
