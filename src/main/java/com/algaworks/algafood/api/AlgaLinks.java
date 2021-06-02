package com.algaworks.algafood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.CozinhaController;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.controller.EstatisticaController;
import com.algaworks.algafood.api.controller.FluxoPedidoController;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.controller.GrupoController;
import com.algaworks.algafood.api.controller.GrupoPermissaoController;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.PermissaoController;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.controller.RestauranteFormaPagamentoController;
import com.algaworks.algafood.api.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.controller.RestauranteProdutoFotoController;
import com.algaworks.algafood.api.controller.RestauranteResponsavelController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.controller.UsuarioGrupoController;

@Component
public class AlgaLinks {

	private static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	public Link linkToPedidos(String rel) {
		var filtroVariables = new TemplateVariables(
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

		var pedidosUrl = linkTo(PedidoController.class).toUri().toString();

		return Link.of(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES.concat(filtroVariables)), rel);
	}
	
	public Link linkToPedidos() {
		return linkToPedidos(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToPedidoConfirmacao(String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).confirmar(codigo)).withRel(rel);
	}
	
	public Link linkToPedidoEntrega(String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).entregar(codigo)).withRel(rel);
	}
	
	public Link linkToPedidoCancelar(String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).cancelar(codigo)).withRel(rel);
	}
	
	public Link linkToRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).buscar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestaurante(Long restauranteId) {
		return linkToRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestaurantes(String rel) {
		return linkTo(RestauranteController.class).withRel(rel);
	}
	
	public Link linkToRestaurantes() {
		return linkToRestaurantes(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestauranteFormaPagamento(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).listar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteFormaPagamento(Long restauranteId) {
		return linkToRestauranteFormaPagamento(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestauranteFormaPagamentoAssociar(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class)
				.associarFormaPagamento(restauranteId, null)).withRel(rel);
	}
	
	public Link linkToRestauranteFormaPagamentoDesassociar(Long restauranteId, Long formaPagamentoId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class)
				.desassociarFormaPagamento(restauranteId, formaPagamentoId)).withRel(rel);
	}
	
	public Link linkToRestauranteResponsaveis(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteResponsavelController.class).listar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestaurnatesPersonalizado() {
		var filtroVariables = new TemplateVariables(
					new TemplateVariable("nome", VariableType.REQUEST_PARAM),
					new TemplateVariable("taxaFreteInicial", VariableType.REQUEST_PARAM),
					new TemplateVariable("taxaFreteFinal", VariableType.REQUEST_PARAM)
				);
		
		var restaurantesUrl = linkTo(RestauranteController.class).toUri().toString();
		
		return Link.of(UriTemplate.of(restaurantesUrl, filtroVariables), IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestauranteAtivar(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).ativar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteInativar(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).inativar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteAbrir(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).abrir(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteFechar(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).fechar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteResponsaveis(Long restauranteId) {
		return linkTo(methodOn(RestauranteResponsavelController.class).listar(restauranteId)).withSelfRel();
	}
	
	public Link linkToRestauranteResponsavelAssociar(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteResponsavelController.class).associar(restauranteId, null)).withRel(rel);
	}
	
	public Link linkToRestauranteResponsavelDesassociar(Long restauranteId, Long responsavelId, String rel) {
		return linkTo(methodOn(RestauranteResponsavelController.class).desassociar(restauranteId, responsavelId)).withRel(rel);
	}
	
	public Link linkToCliente(Long clienteId, String rel) {
		return linkTo(methodOn(UsuarioController.class).buscar(clienteId)).withRel(rel);
	}
	
	public Link linkToCliente(Long clienteId) {
		return linkToCliente(clienteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId)).withRel(rel);
	}
	
	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToFormasPagamento(String rel) {
		return linkTo(FormaPagamentoController.class).withRel(rel);
	}
	
	public Link linkToFormasPagamento() {
		return linkToFormasPagamento(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCidade(Long cidadeId, String rel) {
		return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel);
	}
	
	public Link linkToCidade(Long cidadeId) {
		return linkToCidade(cidadeId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCidades(String rel) {
		return linkTo(methodOn(CidadeController.class).listar()).withRel(rel);
	}
	
	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToProduto(Long restauranteId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class).buscar(restauranteId, produtoId)).withRel(rel);
	}
	
	public Link linkToProduto(Long restauranteId, Long produtoId) {
		return linkToProduto(restauranteId, produtoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToProdutos(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class).listar(restauranteId, null)).withRel(rel);
	}
	
	public Link linkToProdutos(Long restauranteId) {
		return linkToProdutos(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToProdutoFoto(Long restauranteId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteProdutoFotoController.class).buscar(restauranteId, produtoId)).withRel(rel);
	}
	
	public Link linkToProdutoFoto(Long restauranteId, Long produtoId) {
		return linkToProdutoFoto(restauranteId, produtoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToEstado(Long estadoId, String rel) {
		return linkTo(methodOn(EstadoController.class).buscar(estadoId)).withRel(rel);
	}
	
	public Link linkToEstado(Long estadoId) {
		return linkToEstado(estadoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToEstados(String rel) {
		return linkTo(EstadoController.class).withRel(rel);
	}
	
	public Link linkToEstados() {
		return linkToEstados(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCozinhas(String rel) {
		return linkTo(CozinhaController.class).withRel(rel);
	}
	
	public Link linkToCozinhas() {
		return linkToCozinhas(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCozinha(Long cozinhaId, String rel) {
		return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withRel(rel);
	}
	
	public Link linkToCozinha(Long cozinhaId) {
		return linkToCozinha(cozinhaId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToGrupos(String rel) {
		return linkTo(GrupoController.class).withRel(rel);
	}
	
	public Link linkToGrupos() {
		return linkToGrupos(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToGrupoPermissoes(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).listar(grupoId)).withRel(rel);
	}
	
	public Link linkToGrupoPermissoes(Long grupoId) {
		return linkToGrupoPermissoes(grupoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToGrupoPermissaoAssociar(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).associarPermissao(grupoId, null)).withRel(rel);
	}
	
	public Link linkToGrupoPermissaoDesassociar(Long grupoId, Long permissaoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).desassociarPermissao(grupoId, permissaoId)).withRel(rel);
	}
	
	public Link linkToPermissoes(String rel) {
		return linkTo(PermissaoController.class).withRel(rel);
	}
	
	public Link linkToPermissoes() {
		return linkToPermissoes(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToUsuarios(String rel) {
		return linkTo(UsuarioController.class).withRel(rel);
	}
	
	public Link linkToUsuarios() {
		return linkToUsuarios(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToUsuariosGrupos(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
	}
	
	public Link linkToUsuarioGrupo(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
	}
	
	public Link linkToUsuarioGrupo(Long usuarioId) {
		return linkToUsuarioGrupo(usuarioId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToUsuarioGrupoAssociar(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).associar(usuarioId, null)).withRel(rel);
	}
	
	public Link linkToUsuarioGrupoDesassociar(Long usuarioId, Long grupoId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).desassociar(usuarioId, grupoId)).withRel(rel);
	}
	
	public Link linkToEstatisticas(String rel) {
		return linkTo(EstatisticaController.class).withRel(rel);
	}
	
	public Link linkToEstatisticasVendasDiaria(String rel) {
		var filtroVariables = new TemplateVariables(
					new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
					new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
					new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM),
					new TemplateVariable("timeOffset", VariableType.REQUEST_PARAM)
				);
		
		var estatisticasUrl = linkTo(methodOn(EstatisticaController.class).consultarVendasDiarias(null, null)).toUri().toString();
		
		return Link.of(UriTemplate.of(estatisticasUrl, filtroVariables), rel);
	}
}
