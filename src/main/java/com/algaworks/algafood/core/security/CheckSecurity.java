package com.algaworks.algafood.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Cozinhas {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeConsultarCozinhas()")
		public @interface PodeConsultar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeEditarCozinhas()")
		public @interface PodeEditar { }
	}
	
	public @interface Restaurantes {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeConsultarRestaurantes()")
		public @interface PodeConsultar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeGerenciarCadastroRestaurantes()")
		public @interface PodeGerenciarCadastro { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeGerenciarFuncionamentoRestaurantes(#restauranteId)")
		public @interface PodeGerenciarFuncionamento { }
	}
	
	public @interface Pedidos {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeBuscarPedidos()")
		@PostAuthorize("@algaSecurity.podePesquisarPedidos(returnObject.body.cliente.id, returnObject.body.restaurante.id)")
		public @interface PodeBuscar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podePesquisarPedidos(#pedidoFilter.clienteId, #pedidoFilter.restauranteId)")
		public @interface PodePesquisar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeEmitirPedidos")
		public @interface PodeEmitir { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeGerenciarPedidos(#codigoPedido)")
		public @interface PodeGerenciarPedidos { }
	}
	
	public @interface Estados {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeConsultarEstados()")
		public @interface PodeConsultar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
		public @interface PodeEditar { }
	}
	
	public @interface Cidades {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeConsultarCidades()")
		public @interface PodeConsultar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
		public @interface PodeEditar { }
	}
	
	public @interface UsuariosGruposPermissoes {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @algaSecurity.usuarioAutenticadoIgual(#usuarioId)")
		public @interface PodeAlterarPropriaSenha { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
				+ "@algaSecurity.usuarioAutenticadoIgual(#usuarioId))")
		public @interface PodeAlterarUsuario { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeEditarUsuariosGruposPermissoes()")
		public @interface PodeEditar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeConsultarUsuariosGruposPermissoes()")
		public @interface PodeConsultar { }
	}
	
	public @interface Estatisticas {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeConsultarEstatisticas()")
		public @interface PodeConsultar { }
	}
	
	public @interface FormasPagamento {
		
		@PreAuthorize("@algaSecurity.podeConsultarFormasPagamento()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
	}
}
