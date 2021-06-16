package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algaworks.algafood.domain.dto.Mensagem;

import freemarker.template.Configuration;

@Component
public class ProcessadorEmailTemplate {

	@Autowired
	private Configuration freemarkerConfig;
	
	protected String processarTemplate(Mensagem mensagem) {
		try {
			var template = freemarkerConfig.getTemplate(mensagem.getCorpo());
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new EmailException("Não foi possível processar o template do e-mail", e);
		}
	}
}
