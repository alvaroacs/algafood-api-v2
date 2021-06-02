package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AlgaLinks algaLinks;

	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	public PedidoModel toModel(Pedido pedido) {
		var pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		mapper.map(pedido, pedidoModel);

		pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
		
		if (pedido.podeSerConfirmado()) {
			pedidoModel.add(algaLinks.linkToPedidoConfirmacao(pedido.getCodigo(), "confirmar"));
		}
		
		if (pedido.podeSerEntregue()) {
			pedidoModel.add(algaLinks.linkToPedidoEntrega(pedido.getCodigo(), "entregar"));
		}
		
		if (pedido.podeSerCancelado()) {
			pedidoModel.add(algaLinks.linkToPedidoCancelar(pedido.getCodigo(), "cancelar"));
		}
		
		pedidoModel.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

		pedidoModel.getCliente().add(algaLinks.linkToCliente(pedido.getCliente().getId()));

		pedidoModel.getFormaPagamento().add(algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

		pedidoModel.getEnderecoEntrega().add(algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

		pedidoModel.getItens().forEach(item -> item
				.add(algaLinks.linkToProduto(pedido.getRestaurante().getId(), item.getProdutoId(), "produto")));

		return pedidoModel;
	}

}
