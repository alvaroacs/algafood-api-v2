package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.domain.exception.NegocioException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurante")
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;

	@ManyToOne
	@JoinColumn(name = "cozinha_id")
	private Cozinha cozinha;

	@Embedded
	private Endereco endereco;

	@CreationTimestamp
	@Column(name = "data_cadastro")
	private OffsetDateTime dataCadastro;

	@UpdateTimestamp
	@Column(name = "data_atualizacao")
	private OffsetDateTime dataAtualizacao;

	private Boolean ativo = Boolean.TRUE;

	private Boolean aberto = Boolean.FALSE;

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis = new HashSet<>();

	@OneToMany(mappedBy = Produto_.RESTAURANTE)
	private List<Produto> produtos = new ArrayList<>();

	public void ativar() {
		setAtivo(Boolean.TRUE);
	}

	public void inativar() {
		if (!getAberto()) {
			setAtivo(Boolean.FALSE);
		} else {
			throw new NegocioException(
					String.format("0 %s não pode ser desativado, você deve fechá-lo antes", getNome()));
		}
	}

	public void abrir() {
		if (getAtivo()) {
			setAberto(Boolean.TRUE);
		} else {
			throw new NegocioException(String.format("O %s não pode ser aberto, está inativo", getNome()));
		}
	}

	public void fechar() {
		if (getAtivo()) {
			setAberto(Boolean.FALSE);
		} else {
			throw new NegocioException(String.format("O %s não pode ser fechado, está inativo", getNome()));
		}
	}

	public boolean associar(FormaPagamento formaPagamento) {
		return getFormasPagamento().add(formaPagamento);
	}

	public boolean desassociar(FormaPagamento formaPagamento) {
		return getFormasPagamento().remove(formaPagamento);
	}
	
	public boolean associar(Usuario responsavel) {
		return getResponsaveis().add(responsavel);
	}
	
	public boolean desassociar(Usuario usuario) {
		return getResponsaveis().remove(usuario);
	}
	
	public boolean aceitaFormaFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().contains(formaPagamento);
	}
	
	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
		return !aceitaFormaFormaPagamento(formaPagamento);
	}
}
