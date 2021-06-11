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
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		public @interface PodeConsultar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
		public @interface PodeEditar { }
	}
	
	public @interface Restaurantes {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		public @interface PodeConsultar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')")
		public @interface PodeGerenciarCadastro { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_RESTAURANTES') "
				+ "or @algaSecurity.gerenciaRestaurante(#restauranteId))")
		public @interface PodeGerenciarFuncionamento { }
	}
	
	public @interface Pedidos {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
				+ "@algaSecurity.getUsuarioId() == returnObject.body.cliente.id or "
				+ "@algaSecurity.gerenciaRestaurante(returnObject.body.restaurante.id)")
		public @interface PodeBuscar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('CONSULTAR_PEDIDOS') or "
				+ "@algaSecurity.getUsuarioId == #pedidoFilter.clienteId or"
				+ "@algaSecurity.gerenciaRestaurante(#pedidoFilter.restauranteId))")
		public @interface PodePesquisar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		public @interface PodeEmitir { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('GERENCIAR_PEDIDOS') or "
				+ "@algaSecurity.gerenciaRestauranteDoPedido(#codigoPedido))")
		public @interface PodeGerenciarPedidos { }
	}
	
	public @interface Estados {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		public @interface PodeConsultar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
		public @interface PodeEditar { }
	}
	
	public @interface Cidades {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		public @interface PodeConsultar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
		public @interface PodeEditar { }
	}
	
	public @interface UsuariosGruposPermissoes {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @algaSecurity.getUsuarioId() == #usuarioId")
		public @interface PodeAlterarPropriaSenha { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
				+ "@algaSecurity.getUsuarioId() == #usuarioId)")
		public @interface PodeAlterarUsuario { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES')")
		public @interface PodeEditar { }
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES')")
		public @interface PodeConsultar { }
	}
	
	public @interface Estatisticas {
		
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('GERAR_RELATORIOS')")
		public @interface PodeConsultar { }
	}
}
