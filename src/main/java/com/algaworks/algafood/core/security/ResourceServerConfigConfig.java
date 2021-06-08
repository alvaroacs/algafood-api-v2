package com.algaworks.algafood.core.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@EnableWebSecurity
public class ResourceServerConfigConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public JwtDecoder jwtDecoder() {
		var secretKey = new SecretKeySpec("47DEQpj8HBSa-_TImW-5JCeuQeRkm5NMpJWZG3hSuFU".getBytes(), "HmacSHA256");
		
		return NimbusJwtDecoder.withSecretKey(secretKey).build();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated()
			.and()
				.cors()
			.and()
				.oauth2ResourceServer().jwt();
	}
}
