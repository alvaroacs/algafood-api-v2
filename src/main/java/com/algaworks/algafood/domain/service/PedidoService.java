package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpec;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CidadeService cidadeService;

	public Page<Pedido> pesquisar(PedidoFilter pedidoFilter, Pageable pageable) {
		return pedidoRepository.findAll(PedidoSpec.usandoFiltro(pedidoFilter), pageable);
	}

	public Pedido buscar(String codigo) {
		return pedidoRepository.findByCodigo(codigo).orElseThrow(() -> new PedidoNaoEncontradoException(codigo));
	}

	@Transactional
	public Pedido emitir(Pedido pedido) {
		try {
			validarPedido(pedido);
			validarItens(pedido);
			
			pedido.definirFrete();
			pedido.calcularValorTotal();

			return pedidoRepository.save(pedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	private void validarPedido(Pedido pedido) {
		var cliente = usuarioService.buscar(pedido.getCliente().getId());
		var restaurante = restauranteService.buscar(pedido.getRestaurante().getId());
		var formaPagamento = formaPagamentoService.buscar(pedido.getFormaPagamento().getId());
		var cidade = cidadeService.buscar(pedido.getEnderecoEntrega().getCidade().getId());

		pedido.setCliente(cliente);
		pedido.setRestaurante(restaurante);
		pedido.getEnderecoEntrega().setCidade(cidade);

		if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
			throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante",
					formaPagamento.getDescricao()));
		} else {
			pedido.setFormaPagamento(formaPagamento);
		}
	}

	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			var produto = produtoService.buscar(pedido.getRestaurante().getId(), item.getProduto().getId());

			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});
	}
}
