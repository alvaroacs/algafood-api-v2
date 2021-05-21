package com.algaworks.algafoodapi.domain.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafoodapi.domain.dto.Foto;
import com.algaworks.algafoodapi.domain.exception.FotoProdutoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.FotoProduto;
import com.algaworks.algafoodapi.domain.repository.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FotoStorageService fotoStorageService;

	public FotoProduto buscar(Long restauranteId, Long produtoId) {
		return produtoRepository.findFotoById(restauranteId, produtoId)
				.orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
	}

	public Foto servir(Long restauranteId, Long produtoId) {
		var fotoProduto = buscar(restauranteId, produtoId);
		return fotoStorageService.recuperar(fotoProduto.getNomeArquivo()).contentType(fotoProduto.getContentType())
				.tamanho(fotoProduto.getTamanho()).build();
	}

	@Transactional
	public FotoProduto salvar(Long restauranteId, Long produtoId, FotoProduto foto, InputStream inputStream) {
		var fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
		var nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeAntigoArquivo = null;

		if (fotoExistente.isPresent()) {
			foto.setProduto(fotoExistente.get().getProduto());
			nomeAntigoArquivo = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		} else {
			var produto = produtoService.buscar(restauranteId, produtoId);
			foto.setProduto(produto);
		}

		foto.setNomeArquivo(nomeNovoArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();

		var novaFoto = Foto.builder().nomeArquivo(nomeNovoArquivo).inputStream(inputStream)
				.contentType(foto.getContentType()).tamanho(foto.getTamanho()).build();
		fotoStorageService.substituir(nomeAntigoArquivo, novaFoto);

		return foto;
	}

	@Transactional
	public void remover(Long restauranteId, Long produtoId) {
		var foto = buscar(restauranteId, produtoId);
		produtoRepository.delete(foto);
		produtoRepository.flush();

		fotoStorageService.remover(foto.getNomeArquivo());
	}
}
