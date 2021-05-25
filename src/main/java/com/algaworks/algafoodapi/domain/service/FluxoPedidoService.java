package com.algaworks.algafoodapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.dto.Mensagem;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private EnvioEmailService mailService;
	
	@Transactional
	public void confirmar(String codigoPedido) {
		var pedido = pedidoService.buscar(codigoPedido);
		pedido.confirmar();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome().concat(" - Pedido confirmado"))
				.corpo("emails/pedido-confirmado.html")
				.destinatario(pedido.getCliente().getEmail())
				.variavel("pedido", pedido)
				.build();
		
		mailService.enviar(mensagem);
	}
	
	@Transactional
	public void entregar(String codigoPedido) {
		var pedido = pedidoService.buscar(codigoPedido);
		
		pedido.entregar();
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {
		var pedido = pedidoService.buscar(codigoPedido);
		
		pedido.cancelar();
	}
}
