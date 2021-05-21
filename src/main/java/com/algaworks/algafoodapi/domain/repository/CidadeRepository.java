package com.algaworks.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafoodapi.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
