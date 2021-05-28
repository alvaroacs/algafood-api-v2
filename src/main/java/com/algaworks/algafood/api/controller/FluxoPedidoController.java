package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.openapi.controller.FluxoPedidoControllerOpenAPI;
import com.algaworks.algafood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping(path = "/pedidos/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController implements FluxoPedidoControllerOpenAPI {

	@Autowired
	private FluxoPedidoService fluxoPedidoService;
	
	@Override
	@PutMapping("/confirmacao")
	public ResponseEntity<Void> confirmar(@PathVariable String codigoPedido) {
		fluxoPedidoService.confirmar(codigoPedido);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("/entrega")
	public ResponseEntity<Void> entregar(@PathVariable String codigoPedido) {
		fluxoPedidoService.entregar(codigoPedido);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("/cancelar")
	public ResponseEntity<Void> cancelar(@PathVariable String codigoPedido) {
		fluxoPedidoService.cancelar(codigoPedido);
		return ResponseEntity.noContent().build();
	}
}
