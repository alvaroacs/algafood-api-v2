package com.algaworks.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafoodapi.domain.model.Produto;
import com.algaworks.algafoodapi.domain.model.Produto_;
import com.algaworks.algafoodapi.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private RestauranteService restauranteService;

	public List<Produto> listar(Long restauranteId, Boolean isAtivo) {
		var restaurante = restauranteService.buscar(restauranteId);
		
		if (isAtivo) {
			return produtoRepository.findAllByRestaurante(restaurante);
		}
		
		return produtoRepository.findAllByRestauranteAndAtivoTrue(restaurante);
	}

	public Produto buscar(Long restauranteId, Long produtoId) {
		var restaurante = restauranteService.buscar(restauranteId);
		var produto = produtoRepository.findByIdAndRestaurante(produtoId, restaurante)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));

		return produto;
	}

	@Transactional
	public Produto adicionar(Long restauranteId, Produto produto) {
		var restaurante = restauranteService.buscar(restauranteId);
		produto.setRestaurante(restaurante);
		return produtoRepository.save(produto);
	}
	
	@Transactional
	public Produto atualizar(Long restauranteId, Long produtoId, Produto produtoAtualizado) {
		var produto = buscar(restauranteId, produtoId);
		BeanUtils.copyProperties(produtoAtualizado, produto, Produto_.ID, Produto_.RESTAURANTE);
		return produtoRepository.save(produto);
	}
}
