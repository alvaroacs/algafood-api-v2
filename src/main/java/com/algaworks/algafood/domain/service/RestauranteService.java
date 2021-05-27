package com.algaworks.algafood.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Restaurante_;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaService cozinhaService;

	@Autowired
	private CidadeService cidadeService;

	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	public List<Restaurante> listarPersonalizado(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}

	public List<FormaPagamento> listarFormasPagamento(Long restauranteId) {
		return restauranteRepository.findAllFormasPagamento(restauranteId);
	}

	public Restaurante buscar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradaException(restauranteId));
	}

	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		try {
			var cozinha = cozinhaService.buscar(restaurante.getCozinha().getId());
			var cidade = cidadeService.buscar(restaurante.getEndereco().getCidade().getId());

			restaurante.setCozinha(cozinha);
			restaurante.getEndereco().setCidade(cidade);

			return restauranteRepository.save(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@Transactional
	public Restaurante atualizar(Long restauranteId, Restaurante restauranteAtualizado) {
		var restaurante = buscar(restauranteId);
		try {
			var cozinha = cozinhaService.buscar(restauranteAtualizado.getCozinha().getId());
			var cidade = cidadeService.buscar(restauranteAtualizado.getEndereco().getCidade().getId());

			BeanUtils.copyProperties(restauranteAtualizado, restaurante, Restaurante_.ID, Restaurante_.FORMAS_PAGAMENTO,
					Restaurante_.DATA_CADASTRO, Restaurante_.DATA_ATUALIZACAO);

			restaurante.setCozinha(cozinha);
			restaurante.getEndereco().setCidade(cidade);

			return restauranteRepository.save(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@Transactional
	public void ativar(Long restauranteId) {
		var restaurante = buscar(restauranteId);
		restaurante.ativar();
	}

	@Transactional
	public void ativar(List<Long> restaurantesId) {
		try {
			restaurantesId.forEach(this::ativar);
		} catch (RestauranteNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Transactional
	public void inativar(Long restauranteId) {
		var restaurante = buscar(restauranteId);
		restaurante.inativar();
	}

	@Transactional
	public void inativar(List<Long> restaurantesId) {
		try {
			restaurantesId.forEach(this::inativar);
		} catch (RestauranteNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Transactional
	public void abrir(Long restauranteId) {
		var restaurante = buscar(restauranteId);
		restaurante.abrir();
	}

	@Transactional
	public void fechar(Long restauranteId) {
		var restaurante = buscar(restauranteId);
		restaurante.fechar();
	}
}
