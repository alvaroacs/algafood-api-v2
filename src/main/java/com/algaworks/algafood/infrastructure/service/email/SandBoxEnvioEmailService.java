package com.algaworks.algafood.infrastructure.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.dto.Mensagem;

public class SandBoxEnvioEmailService extends SmtpEmailService {

	@Autowired
	private EmailProperties emailProperties;
	
	@Override
	protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
		var mimeMessage = super.criarMimeMessage(mensagem);
		
		var helper = new MimeMessageHelper(mimeMessage, CharEncoding.UTF_8);
		helper.setTo(emailProperties.getSandbox().getDestinatario());
		
		return mimeMessage;
	}
}
