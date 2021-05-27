package com.algaworks.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algaworks.algafood.domain.dto.Mensagem;
import com.algaworks.algafood.domain.event.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.service.EnvioEmailService;

@Component
public class NotificacaoPedidoCanceladoListener {

	@Autowired
	private EnvioEmailService mailService;

	@TransactionalEventListener
	public void aoCancelarPedido(PedidoCanceladoEvent event) {
		var pedido = event.getPedido();

		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome().concat(" - Pedido cancelado"))
				.corpo("emails/pedido-cancelado.html")
				.destinatario(pedido.getCliente().getEmail())
				.variavel("pedido", pedido)
				.build();

		mailService.enviar(mensagem);
	}
}
