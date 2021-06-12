package com.algaworks.algafood.core.security.authorizationserver;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.jwk.JWKSet;

@RestController
public class JwkSetController {

	@Autowired
	private JWKSet jwkSet;
	
	@GetMapping("/.well-known/jwks.json")
	public ResponseEntity<Map<String, Object>> keys() {
		return ResponseEntity.ok(this.jwkSet.toJSONObject());
	}
}
