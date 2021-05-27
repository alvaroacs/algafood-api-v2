package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.model.Usuario_;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public Usuario buscar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}

	@Transactional
	public Usuario adicionar(Usuario usuario) {
		return salvar(usuario);
	}

	@Transactional
	public Usuario atualizar(Long usuarioId, Usuario usuarioAtualizado) {
		var usuario = buscar(usuarioId);
		var email = usuarioAtualizado.getEmail();

		try {
			BeanUtils.copyProperties(usuarioAtualizado, usuario, Usuario_.ID, Usuario_.SENHA, Usuario_.DATA_CADASTRO,
					Usuario_.GRUPOS);

			return salvar(usuario);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(String.format("Já existe um cadastro com o e-mail %s.", email));
		}
	}

	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		var usuario = buscar(usuarioId);

		if (!usuario.getSenha().equals(senhaAtual)) {
			throw new NegocioException("Senha atual informada, não coincide com a senha atual");
		}

		usuario.setSenha(novaSenha);
	}

	private Usuario salvar(Usuario usuarioNovo) {
		var email = usuarioNovo.getEmail();
		var usuario = usuarioRepository.findByEmail(email);

		if (usuario.isPresent() && !usuario.get().equals(usuarioNovo)) {
			throw new NegocioException(String.format("Já existe um cadastro com o e-mail %s.", email));
		}

//		criptografar senha

		return usuarioRepository.save(usuarioNovo);
	}
}
