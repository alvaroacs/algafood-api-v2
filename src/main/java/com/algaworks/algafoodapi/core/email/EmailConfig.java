package com.algaworks.algafoodapi.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafoodapi.domain.service.EnvioEmailService;
import com.algaworks.algafoodapi.infrastructure.service.email.FakeEnvioEmailService;
import com.algaworks.algafoodapi.infrastructure.service.email.SandBoxEnvioEmailService;
import com.algaworks.algafoodapi.infrastructure.service.email.SmtpEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;
	
	@Bean
	public EnvioEmailService envioEmailService() {
		switch (emailProperties.getImplementacao()) {
		case FAKE: {
			return new FakeEnvioEmailService();
		}
		case SANDBOX: {
			return new SandBoxEnvioEmailService();
		}
		case SMTP: {
			return new SmtpEmailService();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + emailProperties.getImplementacao());
		}
	}
}
