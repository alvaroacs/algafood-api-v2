package com.algaworks.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algaworks.algafood.domain.dto.Mensagem;
import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.service.EnvioEmailService;

@Component
public class NotificacaoPedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService mailService;

	@TransactionalEventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		var pedido = event.getPedido();

		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome().concat(" - Pedido confirmado"))
				.corpo("emails/pedido-confirmado.html")
				.destinatario(pedido.getCliente().getEmail())
				.variavel("pedido", pedido)
				.build();

		mailService.enviar(mensagem);
	}
}
