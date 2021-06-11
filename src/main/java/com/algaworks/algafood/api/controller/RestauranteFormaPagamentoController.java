package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.openapi.controller.RestauranteFormaPagamentoControllerOpenAPI;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.service.RestauranteFormaPagamentoService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenAPI {

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@Autowired
	private RestauranteFormaPagamentoService restauranteFormaPagamentoService;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Override
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(@PathVariable Long restauranteId) {
		var formasPagamento = restauranteFormaPagamentoService.listar(restauranteId);
		var formasPagamentoModel = formaPagamentoModelAssembler.toCollectionModel(formasPagamento);
		
		formasPagamentoModel.removeLinks()
			.add(algaLinks.linkToRestauranteFormaPagamento(restauranteId))
			.add(algaLinks.linkToRestauranteFormaPagamentoAssociar(restauranteId, "associar"));
		
		formasPagamentoModel.getContent().forEach(formaPagamentoModel -> {
			formaPagamentoModel.add(algaLinks
					.linkToRestauranteFormaPagamentoDesassociar(restauranteId, formaPagamentoModel.getId(), "desassociar"));
		});
		
		return ResponseEntity.ok(formasPagamentoModel);
	}
	
	@Override
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<Void> associarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteFormaPagamentoService.associarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<Void> desassociarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteFormaPagamentoService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}
}
